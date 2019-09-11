import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Generates a date and time string appropriate for storing in save file.
     * @return string representing date and time in the following format: 23rd September 2019, 3.01am
     */
    private String generateDateAndTimeString() {
        String dateAndTime = "" + by.getDayOfMonth();
        int day = by.getDayOfMonth();

        if (day == 1 || day == 21 || day == 31) {
            dateAndTime += "st";
        } else if (day == 2 || day == 22) {
            dateAndTime += "nd";
        } else if (day == 3 || day == 23) {
            dateAndTime += "rd";
        } else {
            dateAndTime += "th";
        }

        dateAndTime += " of " + by.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " " + by.getYear() + ", ";

        if (by.getHour() > 12) {
            dateAndTime += (by.getHour() % 12) + ""
                    + (by.getMinute() == 0 ? "" : "." + (by.getMinute() < 10 ? "0" : "")
                    + by.getMinute()) + "pm";
        } else if (by.getHour() == 12) {
            dateAndTime += by.getHour() + ""
                    + (by.getMinute() == 0 ? "" : "." + (by.getMinute() < 10 ? "0" : "")
                    + by.getMinute()) + "pm";
        } else if (by.getHour() == 0) {
            dateAndTime += 12 + ""
                    + (by.getMinute() == 0 ? "" : "." + (by.getMinute() < 10 ? "0" : "")
                    + by.getMinute()) + "am";
        } else {
            dateAndTime += by.getHour() + ""
                    + (by.getMinute() == 0 ? "" : "." + (by.getMinute() < 10 ? "0" : "") + by.getMinute()) + "am";
        }

        return dateAndTime;
    }

    /**
     * Generates string for displaying to user.
     * @return string that displays the task in a readable format to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + generateDateAndTimeString() + ")";
    }

    /**
     * Generate string for storing in file.
     * @return string for storing in text file
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + by;
    }

    /**
     * Updates the date time, "by" field of the deadline task.
     * @param newDateTime new date and time to be updated
     * @throws DukeException if the format of the new date time is wrong
     */
    public void editDateTime(String newDateTime) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            by = LocalDateTime.parse(newDateTime, format);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date and time in format: dd/MM/yyyy HHmm");
        }
    }
}