package com.example.datapractice.ENTITY;
import java.time.LocalDate;
import java.util.*;

import static com.example.datapractice.UTILITY.PatientAdapter.*;

public class Patient {
    private int id;
    private final Integer num; // номер мед карты
    private final String snils;
    private final String sex;
    private final String fio;
    private final LocalDate birthDate;
    private String company; //страховая компания
    private final String policy;
    private final String finSource;
    private final List<Integer> expenses = new ArrayList<>();
    private final String fioAbbr;
    private final String age;

    public int getId() {return id;};

    public String getFio() {
        return fio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Integer> getExpenses() {
        return expenses;
    }

    public String getSex() {
        return sex;
    }

    public Integer getNum() {
        return num;
    }

    public String getSnils() {
        return snils;
    }

    public String getPolicy() {
        return policy;
    }

    public String getFinSource() {
        return finSource;
    }

    public String getCompany() {
        return company;
    }

    public Patient(String fio, LocalDate birthDate, Integer sex,
                   Integer num, String smo, String snils, String policy, Integer finSource) {
        this.fio = fio;
        this.fioAbbr = getFioAbbrStr(fio);
        this.birthDate = birthDate;
        this.sex = getSexStr(sex);
        this.num = num;
        this.snils = getSnilsStr(snils);
        this.policy = getPolicyStr(smo, policy);
        this.finSource = String.valueOf(finSource);
        this.age = getAgeStr(birthDate);
    }

//     Patient(String str) {
//        Random random = new Random();
//        String[] strArr = str.split(" ");
//        this.id = Integer.parseInt(strArr[0]);
//        this.fio = strArr[1].concat(" ").concat(strArr[2]).concat(" ").concat(strArr[3]);
//        String[] bd = strArr[4].split("-");
//        this.birthDate = LocalDate.of(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
//        this.sex = strArr[5].equals("1") ? "муж" : "жен";
//        this.num = Integer.parseInt(strArr[6]);
//        this.company = strArr[7];
//        this.snils = strArr[8];
//        this.policy = strArr[9];
//        this.finSource = strArr[10].equals("1") ? "ДМС" : strArr[10].equals("2") ? "ОМС" : "хозрасчет";
//        for (int i = 0; i < 10; i++) {
//            if (random.nextInt( 20) % 2 == 0) {
//                expenses.add(random.nextInt(0, 20) * 100);
//            }
//        }
//    }

    @Override
    public String toString() {
        return "Patient{" + "\n" +
                "id=" + id + "\n" +
                ", num=" + num + "\n" +
                ", snils='" + snils + '\'' + "\n" +
                ", sex='" + sex + '\'' + "\n" +
                ", fio='" + fio + '\'' + "\n" +
                ", birthDate=" + birthDate + "\n" +
                ", company='" + company + '\'' + "\n" +
                ", policy='" + policy + '\'' + "\n" +
                ", finSource='" + finSource + '\'' + "\n" +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(company, patient.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company);
    }
}

