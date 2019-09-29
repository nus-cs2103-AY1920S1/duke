package duke.memo.record.task;

import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.NoTimeException;
import duke.memo.exception.TimeFormatErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date by;
    private static final String TASK_TYPE = "event";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Constructor for Deadline Task.
     * Throw error if there is no time description or wrong format.
     *
     * @param desc  Description for the Deadline Task.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Deadline(String desc) throws DukeException {
        super();
        if (desc.trim().isBlank()) {
            throw new NoDescriptionException(TASK_TYPE);
        }
        try {
            String[] details = desc.split(" /by ", 2);
            description = details[0];
            by = DATE_FORMAT.parse(details[1]);
        } catch (ParseException e) {
            throw new TimeFormatErrorException(TASK_TYPE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoTimeException(TASK_TYPE);
        }
    }

    /**
     * Constructor for Deadline Task.
     * Throw error if there is no time description or wrong format.
     *
     * @param taskDetails  Details for the Deadline Task.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Deadline(String[] taskDetails) throws DukeException {
        super(taskDetails[2], TASK_TYPE);
        isDone = taskDetails[1].equalsIgnoreCase("1");
        try {
            by = DATE_FORMAT.parse(taskDetails[3]);
        } catch (ParseException e) {
            throw new TimeFormatErrorException(TASK_TYPE);
        }
    }

    @Override
    public String getPrintableMsg() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + DATE_FORMAT.format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DATE_FORMAT.format(by) + ")";
    }
}