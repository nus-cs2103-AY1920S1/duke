package task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Deadline extends Task {
    private final String by;
    private static final Pattern PAT = Pattern.compile("(.*) /by (.*)");

    /**
     * Initialises a Deadline from its description and its time.
     */
    private Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }
    
    /**
     * Parses the given line and returns a Deadline constructed from it.
     */
    public static Deadline parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("The description " +
                    "of a deadline cannot be empty.");
        }
        
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("Deadline description " +
                    "must include /by surrounded by spaces.");
        }
        return new Deadline(m.group(1), m.group(2));
    }
    
    /**
     * Returns a string representation of this Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
