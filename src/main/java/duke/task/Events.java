package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Events extends Task {
    String time;
    Date date;

    /**
     * constructor for Deadlines. accesses name and formatter from parent.
     * @param name String name.
     * @param formatter formatter for date and time.
     * @param date date object.
     */
    public Events(String name, SimpleDateFormat formatter, Date date) {
        super(name, formatter);
        this.date = date;
        this.type = "[E]";
        assert date != null;
    }

    /**
     * Overrides toString method to reflect appropriate form of printing task.
     * @return String output of task.
     */
    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[\u2713] " + this.name + "(at:" + formatter.format(this.date) + ")";
        } else {
            output = this.type + "[X] " + this.name + "(at:" + formatter.format(this.date) + ")";
        }
        return output;
    }
}
