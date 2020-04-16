package ru.bulat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bilalov_Bulat
 * @date 17.09.2019
 * @version 1.0
 */

public class Read_XML {
    private static String[] titles = new String[4];
    private static String[] authors = new String[8];
    private static String[] years = new String[4];
    private static String[] prices = new String[4];
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/ru/bulat/first.xml"));
            Pattern title = Pattern.compile(".*<title.*</title>.*");
            Pattern author = Pattern.compile(".*<author>.*</author>.*");
            Pattern year = Pattern.compile(".*<year>.*</year>.*");
            Pattern price = Pattern.compile(".*<price>.*</price>.*");
            String text;
            int o = 0;
            int m = 0;
            int n = 0;
            int r = 0;
            while((text=reader.readLine())!= null) {
                Matcher[] matchers = new Matcher[4];
                matchers[0] = title.matcher(text);
                matchers[1] = author.matcher(text);
                matchers[2] = year.matcher(text);
                matchers[3] = price.matcher(text);
                if (matchers[0].matches()){
                  cuttingRow(text, titles, m);
                  m++;
                }
                if (matchers[1].matches()){
                    cuttingRow(text, authors, n);
                    n++;
                }
                if (matchers[2].matches()){
                    cuttingRow(text, years, r);
                    r++;
                }
                if (matchers[3].matches()){
                    cuttingRow(text, prices, o);
                    o++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }catch (IOException ex){
            System.out.println("Can't read!");
        }
        for (int i = 0; i < prices.length ; i++) {
            double a = Double.parseDouble(prices[i]);
            if (a > 30){
                System.out.println("Title: " + titles[i]);
                System.out.println("Author: " + authors[i]);
                System.out.println("Year: " + years[i]);
                System.out.println("Price: " + prices[i]);
                System.out.println();
            }
        }
    }

    private static void cuttingRow(String text, String[] strings, int m){
        int i = 0;
        while (text.charAt(i) != '>'){
            i++;
        }
        int j = i;
        while (text.charAt(i) != '<'){
            i++;
        }
        strings[m] = text.substring(j+1,i);
    }
}
