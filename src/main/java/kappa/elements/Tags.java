package kappa.elements;

import java.util.HashSet;
import java.util.List;

/**
 * An object that stores multiple tags.
 */
public class Tags {

    private HashSet<String> tagSet;

    public Tags(List<String> tagList) {
        this.tagSet = new HashSet<>();
        this.tagSet.addAll(tagList);
    }

    public Tags() {
        this.tagSet = new HashSet<>();
    }

    /**
     * Returns a formatted string to display all the tags.
     *
     * @return Tags in Formatted String.
     */
    public String toString() {
        if (tagSet.size() == 0) {
            return "None";
        }
        StringBuilder formattedString = new StringBuilder();
        for (String tag: tagSet) {
            formattedString.append("#").append(tag).append(" ");
        }
        return formattedString.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tags)) {
            return false;
        }
        Tags t2 = (Tags) o;
        if (t2 == this) {
            return true;
        }
        return this.tagSet.containsAll(t2.tagSet) && t2.tagSet.containsAll(this.tagSet);


    }
}
