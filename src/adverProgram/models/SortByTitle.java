package adverProgram.models;

import java.util.Comparator;

public class SortByTitle implements Comparator<AD> {

    @Override
    public int compare(AD o1, AD o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
