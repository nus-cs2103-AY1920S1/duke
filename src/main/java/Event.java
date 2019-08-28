import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    private String generateDateAndTimeString() {
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + generateDateAndTimeString() + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + at;
    }
}
