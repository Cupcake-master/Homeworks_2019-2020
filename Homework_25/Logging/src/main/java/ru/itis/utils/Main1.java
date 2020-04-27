package ru.itis.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main1 {
    public static void main(String[] args) {
        //Мы прописали какие значения должны быть для разных языков и получаем их
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.UK);
        String message = bundle.getString("label");

        ResourceBundle bundle1 = ResourceBundle.getBundle("messages", Locale.forLanguageTag("pl-PL"));
        String message1 = bundle1.getString("label");

        System.out.println(message);
        System.out.println(message1);
    }
}
