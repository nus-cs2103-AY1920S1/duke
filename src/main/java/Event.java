import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String at;
    protected Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = formatDate();
    }

    private Date formatDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String[] atSplit = at.split(" ");
        String atSplitDate = atSplit[0];
        String atSplitTime = atSplit[1];

        String[] dateSplit = atSplitDate.split("/");
        String day = dateSplit[0];
        String month = dateSplit[1];
        String year = dateSplit[2];
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        String[] timeSplit = atSplitTime.split("-");
        String startTime = timeSplit[0];
        String endTime = timeSplit[1];

        String startTimeHour = startTime.substring(0, 2);
        String startTimeMinute = startTime.substring(2);

        String endTimeHour = endTime.substring(0, 2);
        String endTimeMinute = endTime.substring(2);

        Date date = new Date();
        String dateString = day + "-" + month + "-" + year;
        String timeString = startTimeHour + ":" + startTimeMinute;
        try {
            date = format.parse(dateString + " " + timeString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}