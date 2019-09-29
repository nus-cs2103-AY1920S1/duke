package duke.memo.record.task;

import duke.memo.exception.DukeException;
import duke.memo.exception.NoDescriptionException;
import duke.memo.exception.NoTimeException;
import duke.memo.exception.TimeFormatErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;
    private static final String TASK_TYPE = "event";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(("dd/MM/yyyy HHmm"));

    /**
     * Constructor for Event Task.
     * Throw error if there is no time description or wrong format.
     *
     * @param desc  Description for the Event Task.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Event(String desc) throws DukeException {
        super();
        if (desc.trim().isBlank()) {
            throw new NoDescriptionException(TASK_TYPE);
        }
        try {
            String[] details = desc.split(" /at ", 2);
            description = details[0];
            at = DATE_FORMAT.parse(details[1]);
        } catch (ParseException e) {
            throw new TimeFormatErrorException(TASK_TYPE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoTimeException(TASK_TYPE);
        }
    }

    /**
     * Constructor for Event Task.
     * Throw error if there is no time description or wrong format.
     *
     * @param taskDetails  Details for the Event Task.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Event(String[] taskDetails) throws DukeException {
        super(taskDetails[2], TASK_TYPE);
        isDone = taskDetails[1].equalsIgnoreCase("1");
        try {
            this.at = DATE_FORMAT.parse(taskDetails[3]);
        } catch (ParseException e) {
            throw new TimeFormatErrorException(TASK_TYPE);
        }
    }

    @Override
    public String getPrintableMsg() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + DATE_FORMAT.format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DATE_FORMAT.format(at) + ")";
    }

}