import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    static String[] suffixes =
        {  "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th",
            "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
            "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
            "30th", "31st" };

    protected Date at;

    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDate() {
        SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
        int day = Integer.parseInt(formatDayOfMonth.format(at));
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("' of' MMMMM yyyy, Ka");
        return " (at: " + suffixes[day] + (DATE_FORMAT.format(at)) + ")";
    }

    @Override
    public boolean getStatus() {
        return isDone;
    }

    public Date getAt() {
        return at;
    }
}