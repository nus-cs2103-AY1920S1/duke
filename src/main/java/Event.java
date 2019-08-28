import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime at;

    /**
     * Constructs an event with description and DateTime string.
     *
     * @param description Description
     * @param at          DateTime String
     * @throws DukeException On empty fields or DateTime string parse failure
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDateTime.parse(at.trim(), super.inDateTimeFormat());
        } catch (DateTimeParseException e) {
            throw new DateTimeParseDukeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(super.outDTF) + ")\n";
    }

    public String childClass() {
        return "event";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + (char) 31 + this.at.format(super.fileDTF);
    }
}
