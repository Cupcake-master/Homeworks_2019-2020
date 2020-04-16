package ru.bulat;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[Ноутбкпла]");
        Matcher matcher1 = pattern.matcher("Ноутбук ");
        Matcher matcher2 = pattern.matcher("Материнская плата");
        Matcher matcher3 = pattern.matcher("wi-fi ");
        Matcher matcher4 = pattern.matcher("Xiaomi");

        System.out.println(matcher1.matches());
        System.out.println(matcher2.matches());
        System.out.println(matcher3.matches());
        System.out.println(matcher4.matches());

        Pattern patternForLink = Pattern.compile("/shop/mid/[0-9]+/");
        Matcher matcherForLink = patternForLink.matcher("/shop/mid/183548266/");
        System.out.println(matcherForLink.matches());

        Set<String> set = new HashSet<>();
        boolean a = set.add("ffff");
        for (String x: set) {
            System.out.println(x);
        }
    }
}
