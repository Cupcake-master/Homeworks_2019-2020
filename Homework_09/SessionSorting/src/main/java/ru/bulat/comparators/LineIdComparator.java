package comparators;

import ru.bulat.Line;

import java.util.Comparator;

public class LineIdComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return Integer.compare(o1.getID(), o2.getID());
    }
}
