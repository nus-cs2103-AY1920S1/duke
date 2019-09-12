package seedu.duke.trackables;

import seedu.duke.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at)  {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + new SimpleDateFormat("d/MM/yyyy HHmm").format(at) + ")";
    }
}
