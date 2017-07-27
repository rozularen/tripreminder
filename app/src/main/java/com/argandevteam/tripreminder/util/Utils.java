package com.argandevteam.tripreminder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by markc on 26/07/2017.
 */

public class Utils {
    public static Date fromLongToDate(long timeInMillis) {
        Date date = new Date(timeInMillis);
        return date;
    }

    public static Date fromStringToDate(String timeStr) {
        String[] dateSplit = timeStr.split("/");
        //TODO: Revisar como parsear fecha en formulario y como se guarda en bd
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        return new Date(year - 1900, month, day);
    }

    public static String fromMillisToText(long millis) {
        return Long.toString(millis);
    }

    public static long fromDateToMillis(Date date) {
        return date.getTime();
    }

    public static String fromDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDateFormat.format(date);
    }
}
