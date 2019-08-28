import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + generateDateAndTimeString() + ")";
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + by;
    }
}