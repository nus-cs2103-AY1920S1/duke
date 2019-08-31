package duke.task;

import duke.exception.DukeException;
import duke.util.Formatter;
import duke.util.Parser;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = Parser.parseDateTime(at);
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + Formatter.standardFormatDateTime(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Formatter.prettyFormatDateTime(at) + ")";
    }
}
