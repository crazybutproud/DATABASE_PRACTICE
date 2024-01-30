package com.example.datapractice.DAO;

import com.example.datapractice.ENTITY.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDao implements IDao {
    private final String DB_url = "jdbc:postgresql://127.0.0.1:5432/patients";
            //"jdbc:postgresql://localhost:54322/patients"; //адрес бд с названием(тип драйвера.название бд.адрес хоста.порт.название)
    private final String NAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String DB_DRIVER = "org.postgresql.Driver"; //название драйвера(не нужен при подключении через maven)
    private final Connection connection; // отвечает за сессию подключения к бд

    public DBDao() {
        try {
            Class.forName(DB_DRIVER); // (не нужен при подключении через maven)
            //this.connection = DriverManager.getConnection(DB_url);
            this.connection = DriverManager.getConnection(DB_url, NAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("NO SUCH DB");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) { // (не нужен при подключении через maven)
            System.out.println("NO JDBC DRIVER");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAll() { // частный случай метода с запросом
        return queryByString("SELECT * FROM PATIENTS"); //отправляем запрос на все данные из бд
    }

    @Override
    public List<Patient> queryByString(String query) { //метод для преобразования строки в запрос
        try {
            Statement statement = connection.createStatement(); // связь с бд
            ResultSet resultSet = statement.executeQuery(query); //итератор

            List<Patient> patientList = new ArrayList<>();

            while (resultSet.next()) { //пока есть следующий пациент
                patientList.add(getFromResultEntry(resultSet));
            }
            return patientList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Patient getFromResultEntry(ResultSet result) throws SQLException { //метод для получения объекта пациент из результата бд
        return new Patient(    //заполняем объект по его конструткору
                result.getString("fio"), //по названию поля(столбца) бд находим значение
                getDateFromString(result.getString("birth_date")),
                Integer.parseInt(result.getString("sex")),
                Integer.parseInt(result.getString("num")),
                result.getString("smo"),
                result.getString("snils"),
                result.getString("policy"),
                Integer.parseInt(result.getString("fin_source"))
        );
    }

    private LocalDate getDateFromString(String date) { //
        String[] birthDate = date.split("-");
        return LocalDate.of(Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]));
    }

    @Override
    public Map<LocalDate,String> getMenFinSource3Birthday_fio () {
        String query = "select fio, birth_date as date from patients where sex = 1 and fin_source = 3";
        try {
            Statement statement = connection.createStatement(); // связь с бд
            ResultSet resultSet = statement.executeQuery(query); //итератор

            Map<LocalDate,String> patientMap = new HashMap<>();

            while (resultSet.next()) { //пока есть следующий пациент
                patientMap.put(getDateFromString(resultSet.getString("date")), resultSet.getString("fio"));
            }
            return patientMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        System.out.println();
    }
}
