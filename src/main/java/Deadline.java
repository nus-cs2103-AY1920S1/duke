import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Deadline extends Task {
    protected String by;
    private static Pattern PAT = Pattern.compile("(.*) /by (.*)");
    
    //@@author Parcly-Taxel
    /**
     * Initialises a Deadline from its description and its time.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }
    
    /**
     * Parses the given line and returns a Deadline constructed from it.
     */
    public static Deadline parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("\u2639 OOPS!!! " +
                    "The description of a deadline cannot be empty.");
        }
        
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("\u2639 OOPS!!! " +
                    "Deadline description must include /by surrounded by spaces.");
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
