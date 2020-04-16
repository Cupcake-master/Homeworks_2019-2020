package ru.bulat.comparators;

import ru.bulat.Line;

import java.util.Comparator;

public class LineCountryComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return o1.getCountry().compareTo(o2.getCountry());
    }
}
