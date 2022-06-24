package com.hrs.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {

    public Date convertStringToDate(String format, String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public String convertDateToString(String format, Date date) {
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }
        return "";
    }

    public Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime( date);
        cal.set( Calendar.HOUR_OF_DAY, 0);
        cal.set( Calendar.MINUTE, 0);
        cal.set( Calendar.SECOND, 0);
        cal.set( Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
