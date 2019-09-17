package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deadline type task with a deadline time.
 */
public class Deadline extends Task {

    private Date by;

    /**
     * Constructor of Deadline task.
     * @param description description of deadline task
     * @param by time of deadline
     * @throws ParseException ParseException
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        assert by.length() > 0;
        this.by = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(by) + ")";
    }
}