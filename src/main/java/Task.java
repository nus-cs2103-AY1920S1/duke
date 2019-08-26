import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String time;

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

    public  String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String type) {
        this.taskType = type;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + "[" + this.getStatusIcon() + "]" + " " + this.description);
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

        if (Integer.parseInt(str[0]) == 1 ) {
            day = "1st";
        } else if (Integer.parseInt(str[0]) == 2) {
            day = "2nd";
        } else if (Integer.parseInt(str[0]) == 3) {
            day = "3rd";
        } else {
            day = str[0] + "th";
        }



        if (str[5].equals("AM")) {
            aa = "am";
        } else {
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
}
