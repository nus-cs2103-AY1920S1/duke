package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A representation of the deadline task.
 */
public class Deadline extends Task {
    private Date byDate;

    /**
     * Public constructor for the Deadline class.
     * Creates an incomplete deadline task.
     *
     * @param description Description of the task.
     * @param by Date and time the task is due.
     * @throws ParseException If by is not of "dd/MM/yyyy HHmm" format.
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.byDate = format.parse(by);
    }

    /**
     * Converts information about the deadline task to String format (for addition to the data file).
     *
     * @return Task information in String format.
     */
    @Override
    public String printToTxtFile() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, date.format(byDate));
    }

    /**
     * Converts information about the deadline task to String format (for printing to the UI).
     *
     * @return Task information in String format.
     */
    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("E, dd/MM/yyyy HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), date.format(byDate));
    }
}
