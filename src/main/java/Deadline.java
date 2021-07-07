import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline object.
 */
public class Deadline extends Task {
    protected LocalDate myDate;
    protected LocalTime myTime;
    protected DateTimeFormatter myFormattedDate;

    /**
     * Constructor for Deadline class.
     * @param description task description.
     * @param date deadline date and time.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.type = "deadline";
        this.symbol = "D";

        String[] dateTimeString = date.split(" ");
        String dateString = dateTimeString[0];
        String year = dateString.split("/")[2];
        String month = dateString.split("/")[1];
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = dateString.split("/")[0];
        if (day.length() == 1) {
            day = "0" + day;
        }
        String timeString = dateTimeString[1];
        String hour = timeString.substring(0, 2);
        String minute = timeString.substring(2, 4);

        myFormattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        myDate = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
        myTime = LocalTime.parse(hour + ":" + minute);
    }

    @Override
    public String toString() {
        return "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + myDate.format(myFormattedDate) + " " + myTime + ")";
    }
}