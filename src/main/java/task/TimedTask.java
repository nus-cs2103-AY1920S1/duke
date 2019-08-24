package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TimedTask extends Task {
    protected String time;

    private static final String[] suffixes =
        { "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th", "10th", "11th", "12th",
          "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th",
          "26th", "27th", "28th", "29th", "30th", "31st" };

    /**
     * Creates a new timed task.
     * @param description Description of the task
     * @param time Time of the task
     */
    public TimedTask(String description, String time) {
        super(description);
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date dateTime = inputFormat.parse(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat("d");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM yyyy");
            SimpleDateFormat hourFormat = new SimpleDateFormat("ha");
            this.time = suffixes[Integer.parseInt(dateFormat.format(dateTime))]
                      + " of " + outputFormat.format(dateTime) + ", " + hourFormat.format(dateTime).toLowerCase();
        } catch (ParseException e) {
            this.time = time;
        }
    }
}
