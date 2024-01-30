package com.example.datapractice;

import com.example.datapractice.DAO.DBDao;
import com.example.datapractice.DAO.IDao;
import com.example.datapractice.ENTITY.Patient;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDao dao = new DBDao();
        List<Patient> patientsALL = dao.getAll();
        patientsALL.forEach(System.out::println);
        List<Patient> patientsWOMAN = dao.queryByString("SELECT * FROM PATIENTS WHERE (sex = 2);");
        patientsWOMAN.forEach(System.out::println);
        dao.getMenFinSource3Birthday_fio();
        dao.closeConnection();
    }
}
