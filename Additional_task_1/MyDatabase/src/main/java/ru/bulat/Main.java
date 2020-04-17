package ru.bulat;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> column = new ArrayList<>();
        column.add("Id");
        column.add("Name");
        column.add("Age");
        column.add("Country");
        column.add("Sex");
        MyNormalDatabase.columnTitleFilling(column);
        MyNormalDatabase.addingAllData("1", "Bulat", "11", "Russia", "Male");
        MyNormalDatabase.addingAllData("2", "Bulat", "12", "Russia", "Male");
        MyNormalDatabase.addingAllData("3", "Bulat", "13", "Russia", "Male");
        MyNormalDatabase.addingAllData("4", "Bulat", "14", "Russia", "Male");
        MyNormalDatabase.addingAllData("5", "Bulat", "15", "Russia", "Male");
        MyNormalDatabase.addingAllData("6", "Bulat", "16", "Russia", "Male");
        MyNormalDatabase.addingAllData("7", "Bulat", "17", "Russia", "Male");
        MyNormalDatabase.addingData(2, "Bulat");
        MyNormalDatabase.addingData(2, "Almaz");
        MyNormalDatabase.addingData(2, "Robert");
        MyNormalDatabase.addingData(2, "Cat");
        MyNormalDatabase.addingData(2, "Dog");
        MyNormalDatabase.addingData(2, "Ruslan");
        MyNormalDatabase.writeToFile();
    }
}
