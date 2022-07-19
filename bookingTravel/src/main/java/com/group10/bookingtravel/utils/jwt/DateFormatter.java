package com.group10.bookingtravel.utils.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static final String PATTERN = "yyyy-MM-dd";

    public static String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        return formatter.format(date);
    }
}
