package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a deadline, a duke.task to be completed by a certain time.
 */
public class Deadline extends Task {
    private final String by;
    private static final Pattern PAT = Pattern.compile("(.+) /by (.+)");

    /**
     * Initialises a Deadline from its description and its time.
     *
     * @param desc A description of the duke.task which is under deadline.
     * @param by The time by which this duke.task must be done.
     */
    private Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }
    
    /**
     * Parses the given line and returns a Deadline constructed from it.
     *
     * @param line The parsed line, which should contain "/by" in its middle.
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
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
