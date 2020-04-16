package ru.bulat;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVFile {
    private static Map<Long, ArrayList<String>> data = new HashMap<>();
    private static String[] headers
            = {"ID", "E-mail", "Password", "Telephone number", "Date of Birth", "Gender", "Country", "About myself"};


    public static void writeToCSVFile(String email, String password, String telephoneNumber, String dateOfBirth,
                                      String gender, String country, String aboutMyself) {
        ArrayList<String> information = new ArrayList<>(7);
        information.add(email);
        information.add(password);
        information.add(telephoneNumber);
        information.add(dateOfBirth);
        information.add(gender);
        information.add(country);
        information.add(aboutMyself);
        long id = (long) (Math.random()*100000);
        data.put(id, information);
        try (FileWriter out = new FileWriter("C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Homework_10\\Entrance\\src\\main\\webapp\\data\\data.csv", false);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (Map.Entry<Long, ArrayList<String>> entry : data.entrySet()) {
                ArrayList<String> arrayList = entry.getValue();
                printer.printRecord(id, arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3),
                        arrayList.get(4), arrayList.get(5), arrayList.get(6));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean userVerification(String email, String password) {
        try {
            Reader in = new FileReader("C:\\Users\\sereg\\Desktop\\My_work\\Homework_on_2_course-practice\\Homework_10\\Entrance\\src\\main\\webapp\\data\\data.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                String someEmail = record.get(1);
                String somePassword = record.get(2);
                if (email.equals(someEmail) && password.equals(somePassword)) return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
