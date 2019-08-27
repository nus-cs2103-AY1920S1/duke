package seedu.duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event extends Task {

    private Date at;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Event(String task, String at) throws DukeException {
        super(task);
        try {
            this.at = dateFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormatter.format(at) + ")";
    }
}
