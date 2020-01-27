package seedu.duke.TaskList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    public String taskType;
    public String description;
    public String time;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public int getStatusNumber() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setTaskType(String type) {
        this.taskType = type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setTime(String time) {
        if (time.contains("/")) {
            DateFormat df = new SimpleDateFormat("d/MM/yyyy HHmm");
            try {
                Date output = df.parse(time);
                String result = new SimpleDateFormat("d 'of' MMMM yyyy, hmm aa").format(output);
                this.time = format(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            this.time = time;
        }
    }

    public String format(String strTime) {
        String[] str = strTime.split(" ");
        String day;
        String aa;
        int time;

        switch (Integer.parseInt(str[0])) {
            case 1:
            day = "1st";
            case 2:
            day = "2nd";
            case 3:
            day = "3rd";
            default:
            day = str[0] + "th";
        }

        switch (str[5]) {

            case "AM":
            aa = "am";
            default:
            aa = "pm";
        }

        if (Integer.parseInt(str[4]) % 100 == 0) {
            time = Integer.parseInt(str[4]) / 100;
        } else {
            time = Integer.parseInt(str[4]);
        }

        return day + " " + str[1] + " " + str[2] + " " + str[3] + " " + time + aa;
    }

    public String toString() {
        return this.taskType;
    }

    public String toActionString() {
        return null;
    }
}
