package com.example.datapractice.DAO;

import com.example.datapractice.ENTITY.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDao {
    List<Patient> getAll(); // список всех пациентов
    List<Patient> queryByString (String query); //собираем строку и делаем по ней запрос
    void closeConnection();
    Map<LocalDate,String> getMenFinSource3Birthday_fio ();
}
