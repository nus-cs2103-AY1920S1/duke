import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Event extends Task {
    protected String by;
    private static Pattern PAT = Pattern.compile("(.*) /at (.*)");
    
    //@@author Parcly-Taxel
    /**
     * Initialises an Event from its description and its time.
     */
    public Event(String desc, String by) {
        super(desc);
        this.by = by;
    }
    
    /**
     * Parses the given line and returns an Event constructed from it.
     */
    public static Event parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("\u2639 OOPS!!! " +
                    "The description of an event cannot be empty.");
        }
        
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("\u2639 OOPS!!! " +
                    "Event description must include /at surrounded by spaces.");
        }
        return new Event(m.group(1), m.group(2));
    }
    
    /**
     * Returns a string representation of this Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
