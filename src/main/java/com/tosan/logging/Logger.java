package com.tosan.logging;


import com.tosan.ui.ConsoleUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String className, String methodName, Object[] params, Object result, long executionTime) {
        String timeStamp = LocalDateTime.now().format(FORMATTER);
        ConsoleUI.prepareTextLog(className, methodName, params, result, executionTime, timeStamp);
    }

}
