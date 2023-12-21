package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatData(LocalDateTime dateTime) {

        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }
}
