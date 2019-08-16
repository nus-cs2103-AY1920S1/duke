import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Event extends Task {
    protected String by;
    private static Pattern PAT = Pattern.compile(
            "event (.*) /at (.*)");
    
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
    public static Event parse(String line) {
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            return null;
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
