package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeWrongDateFormatException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Class used to create and store Deadline tasks.
 * @author Lim Daekoon
 */

public class Deadline extends Task {

    private Date by;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Constructs a new deadline class with 'done' status set to false.
     * @param task Description of the task.
     * @param by Deadline of this task, in String form
     */
    public Deadline(String task, String by) throws DukeException {
        super(task);
        try {
            this.by = dateFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
    }

    /**
     * Constructs a new deadline class with the 'done' status passed in as a parameter.
     * Used when loading data from saved file.
     * @param task Description of the task.
     * @param done Status of the task.
     * @param by Deadline of this task, in String form.
     */
    public Deadline(String task, String done, String by) throws DukeException {
        super(task);
        try {
            this.by = dateFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    /**
     * Converts this Deadline object into storage string form.
     * @return Data of this object, in storage string form.
     */
    public String toStorageString() {
        String output = "D|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName + "|" + dateFormatter.format(this.by);
        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(by) + ")";
    }
}
