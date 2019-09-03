import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    /**
     * Constructor for Deadline tasks
     * @param description description of task
     * @param by when to finish the task by
     * @throws DukeException exception
     */
    public Deadline(String description, String by) throws DukeException {
        super(description, by);
        // If deadline /by is of correct format
        if (isValidDateTimeFormat(by)) {
            try {
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date date = inputFormat.parse(by);
                // Output: 2nd of December 2019, 6pm
                by = displayFormat.format(date);
                this.subDescription = by;
            } catch (ParseException e) {
                throw new DukeException("Unable to recognise date-time provided.");
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + subDescription + ")";
    }

    public String getTaskType() { return "D"; }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword) || subDescription.contains(keyword);
    }

    /**
     * Returns whether description for /by is of
     * recognised date-time format
     * Format: 2/12/2019 1800
     * Which can then be converted to format: 2 December 2019 6:00 pm
     * @param str
     * @return Whether description for /by can be converted
     */    private boolean isValidDateTimeFormat(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyy hhmm");
        try {
            /*str.matches("([1-9]|[1-2][0-9]|3[0-1])/" +
                    "([1-9]|1[0-2])/" +
                    "([0-9]{4})" +
                    "\\s[0-2][0-9]([0-5]{2})")
             */
            inputFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
