package seedu.duke.tasks;//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

import seedu.duke.exceptions.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;
    protected String dateInString;
    protected static final String dateFormat = "dd/MM/yyyy HHmm";
    protected SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

    public Deadline(String description, String dateInString) throws DukeException {
        super(description);
        this.dateInString = dateInString;
        try {
            by = formatter.parse(dateInString);
        } catch (ParseException ex) {
            throw new DukeException("Not a valid time format!!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + " " + super.toString() + " (by: " + dateInString + " )";
    }
}