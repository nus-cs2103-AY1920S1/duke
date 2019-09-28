package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadlines extends Task {

    private Date date;

    /**
     * constructor for Deadlines. accesses name and formatter from parent.
     * @param name String name.
     * @param formatter formatter for date and time.
     * @param date date object.
     */
    public Deadlines(String name, SimpleDateFormat formatter, Date date) {
        super(name, formatter);
        this.type = "[D]";
        this.date = date;
        assert date != null;
    }

    /**
     * Overrides toString method to reflect appropriate form of printing task.
     * @return String output of task.
     */
    @Override
    public String toString() {
        String output;
        if (this.done) {
            output = this.type + "[✓] " + this.name + "(by:" + formatter.format(this.date) + ")";
        } else {
            output = this.type + "[X] " + this.name + "(by:" + formatter.format(this.date) + ")";
        }
        return output;
    }

    /**
     * Overrides saveText method to produce String to be saved in txt file.
     * @return String output.
     */
    @Override
    public String saveText() {
        String output = "D|";
        if (this.done) {
            output += "1|";
        } else {
            output += "0|";
        }
        output += this.name + "|" + formatter.format(this.date);
        return output;
    }
}

