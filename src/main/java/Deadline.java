package seedu.duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {

    protected Date by;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Deadline(String task, String by) throws DukeException {
        super(task);
        try {
            this.by = dateFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
    }

    public Deadline(String task, String done, String by) {
        super(task);
        this.by = by;
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    public String toStorageString() {
        String output = "D|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName + "|" + this.by;
        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(by) + ")";
    }
}
