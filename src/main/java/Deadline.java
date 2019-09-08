import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class for deadline tasks.
 * @author Choong Yong Xin
 */

public class Deadline extends Task {

    Date deadlineInDateFormat;
    String deadlineInStringFormat;

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline description.
     * @param deadlineInStringFormat Date that task is due.
     * @throws WrongDateFormatDukeException when date supplied is not in dd/MM/yyyy HHmm format.
     */
    public Deadline(String description, String deadlineInStringFormat) throws WrongDateFormatDukeException {
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.deadlineInDateFormat = dateFormat.parse(deadlineInStringFormat);
            this.deadlineInStringFormat = deadlineInStringFormat;
        } catch (Exception e) {
            throw new WrongDateFormatDukeException();
        }
    }

    /**
     * Returns string for task display.
     *
     * @return Display string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineInStringFormat + ")";
    }

    /**
     * Returns string for file writing.
     *
     * @return String to be saved.
     */
    @Override
    public String stringForAppend() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + deadlineInStringFormat;
    }
}