package duke.task;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date givenTime;
    private SimpleDateFormat df;

    public Deadline(char taskType, String taskDescription, boolean isDone, String givenTime) {
        super(taskType, taskDescription, isDone);

        try {
            df = new SimpleDateFormat("dd/mm/yyyy Hm");
            this.givenTime = df.parse(givenTime, new ParsePosition(0));
        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Deadline(String taskDescription, boolean isDone, String givenTime) {
        super(taskDescription, isDone);

        try {
            df = new SimpleDateFormat("dd/mm/yyyy Hm");
            this.givenTime = df.parse(givenTime, new ParsePosition(0));
        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    @Override
    public String printTask() {
        return "[" + getFirstCharTask() +
                "][" + getIcon() + "] " + getTaskDescription() + " (by: " + getGivenTime() + ")";
    }

    public void setGivenTime(String time) throws ParseException {
        this.givenTime = df.parse(time);
    }

    public String getGivenTime() {
        return df.format(givenTime);
    }
}
