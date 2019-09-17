import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDate myDate;
    protected LocalTime myTime;
    protected DateTimeFormatter myFormatDate;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.type = "deadline";

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
        return "[D][" + getStatusIcon() + "]" + description + " (by: " + myDate.format(myFormatDate) + " " + myTime + ")";
    }
}