package adverProgram.models;

import java.util.Comparator;

public class SortByCreatDate implements Comparator<AD> {

    @Override
    public int compare(AD o1, AD o2) {
        return o1.getCreatedDate().compareTo(o2.getCreatedDate());
    }
}
