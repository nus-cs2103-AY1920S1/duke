package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidDateTimeDukeException;
import duke.exception.InvalidTaskDukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoAfter extends Task {

    /**
     * Represents date and time of DoAfter.
     */
    private Date afterWhen;

    /**
     * Constructor of DoAfter object.
     *
     * @param name      Name of DoAfter.
     * @param afterWhen afterWhen of DoAfter.
     * @throws EmptyTaskDukeException       If name is empty.
     * @throws InvalidDateTimeDukeException If doAfter does not follow DD/MM/YYYY HHMM.
     * @throws InvalidTaskDukeException     If doAfter is empty.
     */
    public DoAfter(String name, String afterWhen) throws EmptyTaskDukeException, InvalidTaskDukeException,
            InvalidDateTimeDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("doafter");
        }
        if (afterWhen == null) {
            throw new InvalidTaskDukeException("doafter");
        }
        try {
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.afterWhen = inputFormatter.parse(afterWhen);
        } catch (ParseException e) {
            throw new InvalidDateTimeDukeException("doafter");
        }
    }

    /**
     * Accessor to get due date (afterWhen) of DoAfter.
     *
     * @return String representation of date after.
     */
    public String getAfterWhen() {
        DateFormat storageFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String output = storageFormatter.format(this.afterWhen);
        return output;
    }

    @Override
    public String toString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd MMMM YYYY, h.mmaa");
        String output = outputFormatter.format(this.afterWhen);
        StringBuilder stringBuilder = new StringBuilder("[DA]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(output);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
