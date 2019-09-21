package duke.tasks;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Deadline class that can be created by the user. Handles all Duke.tasks with deadlines.
 */
public class Deadline extends Task {
    
    protected Date by;
    protected SimpleDateFormat format = new SimpleDateFormat();
    
    /**
     * Class constructor.
     *
     * @param description details of the respective task.
     * @param by          deadline date
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }
    
    /**
     * Prints the deadline with the status, the description and the deadline date together.
     */
    @Override
    public String toString() {
        format = new SimpleDateFormat("EEEE, MMM d, HH:mm");
        return "[D]" + super.toString() + "(by: " + format.format(by) + ")";
    }
}