package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * one of the tasks used to record deadlines
 */
public class Deadline extends Task {
    private Date by;

    /**
     * A constructor to write in date and content of the task.
     * @param description the content of the task
     * @param by the due date, with a fixed format
     * @throws ParseException
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    /**
     * the Overriding method
     * @return String that in deadline format
     */
    @Override
    public String toString() {
        return "D -- " + super.toString() + " -- " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(by);
    }
}
