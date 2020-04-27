package ru.itis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;

@Slf4j
public class Main {
    public static void main(String[] args) {
        PropertyConfigurator.
                configure("C:\\Users\\sereg\\Desktop\\My_work\\Homeworks_2019-2020\\Homework_24\\Homework_13" +
                        "\\src\\main\\resources\\log4j.properties");
        log.debug("Sample debug message");
        log.info("Sample info message");
        log.error("Sample error message");
    }
}
