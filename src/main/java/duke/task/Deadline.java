package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidDateTimeDukeException;
import duke.exception.InvalidTaskDukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /**
     * Represents due date (byWhen) of the Deadline.
     */
    private Date byWhen;

    /**
     * Constructor of Deadline object.
     *
     * @param name   Name of Deadline.
     * @param byWhen byWhen of Deadline.
     * @throws EmptyTaskDukeException       If name is empty.
     * @throws InvalidDateTimeDukeException If byWhen does not follow DD/MM/YYYY HHMM.
     * @throws InvalidTaskDukeException     If byWhen is empty.
     */
    public Deadline(String name, String byWhen) throws EmptyTaskDukeException, InvalidTaskDukeException,
            InvalidDateTimeDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("deadline");
        }
        if (byWhen == null) {
            throw new InvalidTaskDukeException("deadline");
        }
        try {
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.byWhen = inputFormatter.parse(byWhen);
        } catch (ParseException e) {
            throw new InvalidDateTimeDukeException("deadline");
        }
    }

    /**
     * Accessor to get due date (byWhen) of Deadline.
     *
     * @return String representation of due date.
     */
    public String getByWhen() {
        DateFormat outputFormatter = new SimpleDateFormat("dd MMMM YYYY, hh.mmaa");
        String output = outputFormatter.format(this.byWhen);
        return output;
    }

    /**
     * Gives appropriate representation of Deadline.
     *
     * @return String representation of Deadline. Includes type of Task, isDone, name of Task and byWhen.
     */
    @Override
    public String toString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd MMMM YYYY, h.mmaa");
        String output = outputFormatter.format(this.byWhen);
        StringBuilder stringBuilder = new StringBuilder("[DL]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(output);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}