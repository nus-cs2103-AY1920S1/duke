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
    }

    /**
     * Overrides toString method to reflect appropriate form of printing task.
     * @return String output of task.
     */
    public String toString() {
        String output = "";
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(at:" + formatter.format(this.date) + ")";
        } else {
            output = this.type + "[X] " + this.name + "(at:" + formatter.format(this.date) + ")";
        }
        return output;
    }

    @Override
    public String saveText() {
        String output = "E|";
        if (this.done) {
            output += "1|";
        } else {
            output += "0|";
        }
        output += this.name + "|" + formatter.format(this.date);
        return output;
    }
}
