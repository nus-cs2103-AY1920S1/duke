import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Deadline extends Task {
    private Date by;

    /**
     * Creates a new Deadline with the given description and due date.
     * @param description       Task to be completed.
     * @param by                Due date for the Deadline.
     */
    Deadline(String description, String by) {
        super(description);
        parse(by);
    }

    /**
     * Sets the due date of the current deadline to the date represented in the
     * given String. If the input format is invalid, due date is set to the
     * current instant.
     * @param date  Valid date string, as specified in DukeFormatter.DATE_FORMATS.
     */
    private void parse(String date) {
        for (SimpleDateFormat format : DukeFormatter.DATE_FORMATS) {
            if (by != null) {
                break;
            }
            try {
                by = format.parse(date);
            } catch (ParseException e) {
                // do nothing and try the next format
            }
        }

        if (by == null) {
            by = new Date();
        }
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and deadline.
     * @return  String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", by) + ")";
    }
}
