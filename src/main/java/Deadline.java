import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    static String[] suffixes =
        {  "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th",
            "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
            "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
            "30th", "31st" };

    protected Date by;

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDate() {
        SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
        int day = Integer.parseInt(formatDayOfMonth.format(by));
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("' of' MMMMM yyyy, Ka");
        return " (by: " + suffixes[day] + (DATE_FORMAT.format(by)) + ")";
    }

    @Override
    public boolean getStatus() {
        return isDone;
    }

    public Date getBy() {
        return by;
    }
}