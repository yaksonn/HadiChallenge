package com.hadichallenge.yakson.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {

    private static DateTimeHelper instance;

    public static DateTimeHelper getInstance() {
        if (instance == null) {
            instance = new DateTimeHelper();
        }
        return instance;
    }

    public static String getDateWithMonthName(String dateString) {

        SimpleDateFormat formatterActual = new SimpleDateFormat("yyyy-MM-dd",new Locale("tr"));
        SimpleDateFormat formatterExpected = new SimpleDateFormat("MMMM yyyy",new Locale("tr"));

        try {
            Date dateActual = formatterActual.parse(dateString);
            String dateExpected = formatterExpected.format(dateActual);
            return dateExpected;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
