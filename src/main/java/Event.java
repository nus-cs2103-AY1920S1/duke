import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns appropriate date and time string suitable for storing.
     * @return string representing date and time in the following format: 23rd September 2019, 3.01am
     */
    protected String generateDateAndTimeString() {
        String dateAndTime = "" + at.getDayOfMonth();
        int day = at.getDayOfMonth();

        if (day == 1 || day == 21 || day == 31) {
            dateAndTime += "st";
        } else if (day == 2 || day == 22) {
            dateAndTime += "nd";
        } else if (day == 3 || day == 23) {
            dateAndTime += "rd";
        } else {
            dateAndTime += "th";
        }

        dateAndTime += " of " + at.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " " + at.getYear() + ", ";

        if (at.getHour() > 12) {
            dateAndTime += (at.getHour() % 12) + ""
                    + (at.getMinute() == 0 ? "" : "." + (at.getMinute() < 10 ? "0" : "")
                    + at.getMinute()) + "pm";
        } else if (at.getHour() == 12) {
            dateAndTime += at.getHour() + ""
                    + (at.getMinute() == 0 ? "" : "." + (at.getMinute() < 10 ? "0" : "")
                    + at.getMinute()) + "pm";
        } else if (at.getHour() == 0) {
            dateAndTime += 12 + ""
                    + (at.getMinute() == 0 ? "" : "." + (at.getMinute() < 10 ? "0" : "")
                    + at.getMinute()) + "am";
        } else {
            dateAndTime += at.getHour() + ""
                    + (at.getMinute() == 0 ? "" : "." + (at.getMinute() < 10 ? "0" : "") + at.getMinute()) + "am";
        }

        return dateAndTime;
    }

    /**
     * Returns string that displays the task in a readable format to the user.
     * @return string that displays the task in a readable format to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + generateDateAndTimeString() + ")";
    }

    /**
     * Returns string for storing in text file.
     * @return string for storing in text file
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + at;
    }

    /**
     * Updates the date time, "at" field of the event task.
     * @param newDateTime new date and time to be updated
     * @throws DukeException if the format of the new date time is wrong
     */
    public void editDateTime(String newDateTime) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            at = LocalDateTime.parse(newDateTime, format);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date and time in format: dd/MM/yyyy HHmm");
        }
    }
}
