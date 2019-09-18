import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a form of task
 */
public class Event extends Task {

    protected LocalDate myDate;
    protected LocalTime myTime;
    protected DateTimeFormatter myFormatDate;

    /**
     * A task which has a starting date (at when)
     * @param description the information of the given task
     * @param date the starting date of the task
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.type = "event";

        String[] dateAndTime = date.split(" ");
        String dateString = dateAndTime[0];

        String year = dateString.split("/")[2];
        String month = dateString.split("/")[1];
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = dateString.split("/")[0];
        if (day.length() == 1) {
            day = "0" + day;
        }

        String timeString = dateAndTime[1];
        String hour = timeString.substring(0,2);
        String minute = timeString.substring(2,4);

        myFormatDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        myDate = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
        myTime = LocalTime.parse(hour + ":" + minute);

    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "]" + description + " (at: " + myDate.format(myFormatDate) + " " + myTime + ")";
    }
}