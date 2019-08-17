package task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing an event that will occur at or around a specified time.
 */
public class Event extends Task {
    private final String at;
    private static final Pattern PAT = Pattern.compile("(.+) /at (.+)");

    /**
     * Initialises an Event from its description and its time.
     *
     * @param desc A description of the event.
     * @param at The time at which this event happens.
     */
    private Event(String desc, String at) {
        super(desc);
        this.at = at;
    }
    
    /**
     * Parses the given line and returns an Event constructed from it.
     *
     * @param line The parsed line, which should contain "/at" in its middle.
     */
    public static Event parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("The description " +
                    "of an event cannot be empty.");
        }
        
        Matcher m = PAT.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("Event description " +
                    "must include /at surrounded by spaces.");
        }
        return new Event(m.group(1), m.group(2));
    }
    
    /**
     * Returns a string representation of this Event.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
