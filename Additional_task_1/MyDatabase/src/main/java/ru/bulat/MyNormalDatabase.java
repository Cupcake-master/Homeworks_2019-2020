package ru.bulat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MyNormalDatabase {
    private static final String path = "C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Additional_task_1\\MyDatabase\\src\\main\\java\\ru\\bulat\\bd.txt";
    private static List<String> columnNamesDatabase;
    private static Map<String, String> column1 = new TreeMap<>();
    private static Map<String, String> column2 = new TreeMap<>();
    private static Map<String, String> column3 = new TreeMap<>();
    private static Map<String, String> column4 = new TreeMap<>();
    private static Map<String, String> column5 = new TreeMap<>();
    private static List<String> allDataColumn1;
    private static List<String> allDataColumn2;
    private static List<String> allDataColumn3;
    private static List<String> allDataColumn4;
    private static List<String> allDataColumn5;
    private static int max;


    public static void columnTitleFilling(ArrayList<String> columnNames) {
        columnNamesDatabase = columnNames;
    }

    public static void addingData(int column, String data) {
        switch (column) {
            case 1:
                addingColumn1(data);
                break;
            case 2:
                addingColumn2(data);
                break;
            case 3:
                addingColumn3(data);
                break;
            case 4:
                addingColumn4(data);
                break;
            case 5:
                addingColumn5(data);
                break;
        }
    }

    public static void addingAllData(String data1, String data2, String data3, String data4, String data5) {
        addingColumn1(data1);
        addingColumn2(data2);
        addingColumn3(data3);
        addingColumn4(data4);
        addingColumn5(data5);
    }

    private static void addingColumn1(String data) {
        column1.put(data, "");
    }

    private static void addingColumn2(String data) {
        column2.put(data, "");
    }

    private static void addingColumn3(String data) {
        column3.put(data, "");
    }

    private static void addingColumn4(String data) {
        column4.put(data, "");
    }

    private static void addingColumn5(String data) {
        column5.put(data, "");
    }

    public static ArrayList<String> getDataByColumn1(String data) {
        return SELECT(allDataColumn1, data, allDataColumn2, allDataColumn3, allDataColumn4, allDataColumn5);
    }

    public static ArrayList<String> getDataByColumn2(String data) {
        return SELECT(allDataColumn2, data, allDataColumn1, allDataColumn3, allDataColumn4, allDataColumn5);
    }

    public static ArrayList<String> getDataByColumn3(String data) {
        return SELECT(allDataColumn3, data, allDataColumn1, allDataColumn2, allDataColumn4, allDataColumn5);
    }

    public static ArrayList<String> getDataByColumn4(String data) {
        return SELECT(allDataColumn4, data, allDataColumn1, allDataColumn2, allDataColumn3, allDataColumn5);
    }

    public static ArrayList<String> getDataByColumn5(String data) {
        return SELECT(allDataColumn5, data, allDataColumn1, allDataColumn2, allDataColumn3, allDataColumn4);
    }

    private static ArrayList<String> SELECT(List<String> list, String data, List<String> list1, List<String> list2,
                                            List<String> list3, List<String> list4) {
        gettingAllValues();
        int index = -1;
        if (list.contains(data)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(data)) {
                    index = i;
                }
            }
        }
        if (index != -1) {
            indexCheck(index, list1);
            indexCheck(index, list2);
            indexCheck(index, list3);
            indexCheck(index, list4);
            return new ArrayList<>(Arrays.asList(list1.get(index), list2.get(index),
                    list3.get(index), list4.get(index)));
        } else {
            return null;
        }
    }

    private static void indexCheck(int index, List<String> list) {
        if (index >= list.size()) list.add(index, "null");
    }

    private static void gettingAllValues() {
        allDataColumn1 = new ArrayList<>(column1.keySet());
        allDataColumn2 = new ArrayList<>(column2.keySet());
        allDataColumn3 = new ArrayList<>(column3.keySet());
        allDataColumn4 = new ArrayList<>(column4.keySet());
        allDataColumn5 = new ArrayList<>(column5.keySet());
    }

    public static boolean dataDeletionByColumn1(String data) {
        return DELETE(data, allDataColumn1, column1);
    }

    public static boolean dataDeletionByColumn2(String data) {
        return DELETE(data, allDataColumn2, column2);
    }

    public static boolean dataDeletionByColumn3(String data) {
        return DELETE(data, allDataColumn3, column3);
    }

    public static boolean dataDeletionByColumn4(String data) {
        return DELETE(data, allDataColumn4, column4);
    }

    public static boolean dataDeletionByColumn5(String data) {
        return DELETE(data, allDataColumn5, column5);
    }

    public static int dataDeletionByKey(int column, String key) {
        gettingAllValues();
        boolean check = false;
        int index = -1;
        int success = 0;
        index = up(column, false,key);
        if (index != -1){
            success = DELETEbyIndex(index, column1, key, success);
            success = DELETEbyIndex(index, column2, key, success);
            success = DELETEbyIndex(index, column3, key, success);
            success = DELETEbyIndex(index, column4, key, success);
            success = DELETEbyIndex(index, column5, key, success);

        }
        return success;
    }

    private static int DELETEbyIndex(int index, Map<String, String> map, String key, int success){
        if (index <= map.size()){
            map.remove(key);
            success++;
        }
        return success;
    }

    private static int findingTheIndex(List<String> list, String data){
        int index = - 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(data)) {
                index = i;
            }
        }
        return index;
    }

    private static boolean DELETE(String data, List<String> list, Map<String, String> map) {
        gettingAllValues();
        if (list.contains(data)) {
            map.remove(data);
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean UPDATEbyColumn(int column, String data){
        up(column, false, data);
        return true;
    }

    private static int up(int column, boolean check, String key){
        int index = -1;
        switch (column) {
            case 1:
                check = allDataColumn1.contains(key);
                index = findingTheIndex(allDataColumn1, key);
                break;
            case 2:
                check = allDataColumn2.contains(key);
                index = findingTheIndex(allDataColumn1, key);
                break;
            case 3:
                check = allDataColumn3.contains(key);
                index = findingTheIndex(allDataColumn1, key);
                break;
            case 4:
                check = allDataColumn4.contains(key);
                index = findingTheIndex(allDataColumn1, key);
                break;
            case 5:
                check = allDataColumn5.contains(key);
                index = findingTheIndex(allDataColumn1, key);
                break;
        }
        return index;
    }


    public static void writeToFile() {
        gettingAllValues();
        max = Collections.max(Arrays.asList(allDataColumn1.size(), allDataColumn2.size(), allDataColumn3.size(), allDataColumn4.size(), allDataColumn5.size()));
        List<Integer> allSizes = new ArrayList<>();
        fillingOutNull(allDataColumn1);
        fillingOutNull(allDataColumn2);
        fillingOutNull(allDataColumn3);
        fillingOutNull(allDataColumn4);
        fillingOutNull(allDataColumn5);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            StringBuilder totalColumnNames = new StringBuilder();
            for (String s : columnNamesDatabase) {
                totalColumnNames.append(s).append(" ");
            }
            writer.write(totalColumnNames.toString() + "\n");
            StringBuilder totalInformation = new StringBuilder();
            for (int i = 0; i < max; i++) {
                totalInformation.append(allDataColumn1.get(i)).append(" ").append(allDataColumn2.get(i)).append(" ").append(allDataColumn3.get(i)).append(" ").append(allDataColumn4.get(i)).append(" ").append(allDataColumn5.get(i)).append("\n");
            }
            writer.write(totalInformation.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillingOutNull(List<String> list) {
        for (int i = list.size(); i < max; i++) {
            list.add("null");
        }
    }
}
