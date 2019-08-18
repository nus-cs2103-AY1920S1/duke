package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a deadline, a task to be completed by a certain time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final Pattern PAT = Pattern.compile("(.+) /by (.+)");
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm");

    /**
     * Initializes a Deadline from its description and its time.
     *
     * @param desc A description of the task which is under deadline.
     * @param by The time by which this task must be done.
     */
    Deadline(String desc, LocalDateTime by) {
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

        try {
            return new Deadline(m.group(1), LocalDateTime.parse(m.group(2), inputFormatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Time must be in the format day#/month#/yyyy hhmm.");
        }
    }
    
    /**
     * Returns a string representation of this Deadline.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }

    /**
     * Exports this Deadline for saving to disk.
     *
     * @return A string representation of this task containing the type marker D and a time.
     */
    @Override
    public String export() {
        return "D|" + super.export() + "|" + by.format(inputFormatter);
    }
}
