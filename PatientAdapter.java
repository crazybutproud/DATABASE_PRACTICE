package com.example.datapractice.UTILITY;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientAdapter {
    public static String getSexStr(int isMan) { // возвращает пол строкой
        return (isMan == 1) ? "МУЖ" : "ЖЕН";
    }

    public static String getPolicyStr(String smo, String policy) { // получаем полис строкой
        return smo + " - " + policy;
    }

    public static String getAgeStr(LocalDate date) {
        return getAgeFormStr(LocalDate.now().compareTo(date));
    }

    private static String getAgeFormStr(int age) {
        int lastDigit = age % 10;
        if (age % 100 >= 11 && age % 100 <= 14)
            return String.valueOf(age).concat(" лет");
        if (lastDigit == 1)
            return String.valueOf(age).concat(" год");
        else if(lastDigit == 0 || lastDigit >= 5)
            return String.valueOf(age).concat(" лет");
        else if(lastDigit >= 2)
            return String.valueOf(age).concat(" года");
        return "";
    }

    public static String getDateStringFromSQLFormat(LocalDate date) { // получаем дату в нужном формате строкой
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dtf.format(date);
    }

    public static String getDateSQLFormat(String date) {
        String[] dateArr = date.split("\\.");
        LocalDate bd = LocalDate.of(Integer.parseInt(dateArr[2]),
                Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0]));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(bd);
    }

    public static String getSnilsStr(String snils) { // получаем снилс строкой
        StringBuilder sb = new StringBuilder(snils);
        return sb.insert(3,"-").insert(7,"-").insert(11, "-").toString();
    }

    public static String getFioAbbrStr(String fio) { // получаем фио аббревиатурой
        StringBuilder result = new StringBuilder();
        String[] fioArr = fio.split(" ");
        result
                .append(fioArr[0])
                .append(" ")
                .append(fioArr[1].charAt(0))
                .append(". ")
                .append(fioArr[2].charAt(0))
                .append(".");
        return result.toString();
    }
}

