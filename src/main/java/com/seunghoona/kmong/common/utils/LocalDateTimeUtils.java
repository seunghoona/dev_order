package com.seunghoona.kmong.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    public static String nowBasicFormat() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static String nowYear() {
        return String.valueOf(LocalDateTime.now()
                .getYear());
    }
}
