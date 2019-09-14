import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(boolean done, String description, String at) throws DateTimeParseException {
        super(description);
        this.at = parseTime(at.trim());
        this.isDone = done;
    }

    public Event(String description, String at) throws DateTimeParseException {
        this(false, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String saveFormat() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, getDesc(), at);
    }

    /**
     * Takes in an array of string, consist of space-split strings from a saved
     * input. Returns a Event object.
     * 
     * @param tokens an array of strings
     * @return an {@link Optional} {@link Event}.
     *
     * @throws NumberFormatException  if the number representing done is not 1 or 0
     * @throws DateTimeParseException if the date format is illegal
     */
    public static Event fromFormattedString(String[] tokens) throws NumberFormatException, DateTimeParseException {
        boolean done = Integer.parseInt(tokens[1]) == 1;
        Event event = new Event(done, tokens[2], tokens[3]);
        return event;
    }
}
