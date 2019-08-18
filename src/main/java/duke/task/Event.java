package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing an event that will occur at or around a specified time.
 */
public class Event extends Task {
    private final LocalDateTime at;
    private static final Pattern PAT = Pattern.compile("(.+) /at (.+)");
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm");

    /**
     * Initializes an Event from its description and its time.
     *
     * @param desc A description of the event.
     * @param at The time at which this event happens.
     */
    Event(String desc, LocalDateTime at) {
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

        try {
            return new Event(m.group(1), LocalDateTime.parse(m.group(2), inputFormatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Time must be in the format day#/month#/yyyy hhmm.");
        }
    }
    
    /**
     * Returns a string representation of this Event.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(displayFormatter) + ")";
    }

    /**
     * Exports this Event for saving to disk.
     *
     * @return A string representation of this task containing the type marker E and a time.
     */
    @Override
    public String export() {
        return "E|" + super.export() + "|" + at;
    }
}
