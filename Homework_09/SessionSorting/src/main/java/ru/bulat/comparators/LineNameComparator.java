package comparators;

import ru.bulat.Line;

import java.util.Comparator;

public class LineNameComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
