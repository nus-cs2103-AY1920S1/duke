package seedu.duke.task;

import seedu.duke.parser.DateParser;
import java.util.Date;

public class Event extends Task {
    protected String strAt;
    protected Date at;

    public Event(String description, String strAt) {
        super(description);
        this.strAt = strAt;
        this.at = new DateParser().parseDate(strAt);
    }

    @Override
    public String toFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.strAt + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + strAt + ")";
    }
}
