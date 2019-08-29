package seedu.duke.tasks;//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

import seedu.duke.exceptions.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;
    protected String dateInString;
    protected static final String dateFormat = "dd/MM/yyyy HHmm";
    protected SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

    public Event(String description, String dateInString) throws DukeException {
        super(description);
        this.dateInString = dateInString;
        try {
            at = formatter.parse(dateInString);
        } catch (ParseException ex) {
            throw new DukeException("Not a valid time format!!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + dateInString + " )";
    }
}