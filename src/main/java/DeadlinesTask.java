import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;

public class DeadlinesTask extends Task {
    String taskName;
    String taskTime;

    public DeadlinesTask(String task) {
        super(task);
        String[] taskSplit = task.split("/by");
        if(taskSplit.length < 2) {
            throw new EmptyDescriptionDukeException("deadline", "/by");
        } else if (taskSplit[0].equals("")) {
            throw new EmptyDescriptionDukeException("deadline");
        }
        taskName = taskSplit[0].trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        taskTime = taskSplit[1].trim();

        try {
            Date time = formatter.parse(taskTime);
            formatter = new SimpleDateFormat("dd MMMM yyyy, ha");
            taskTime = formatter.format(time);
        } catch (ParseException err) {
            throw new InvalidTimeDukeException();
        }
    }
    public DeadlinesTask(String isCompleted, String taskName, String taskDesc) {
        super(taskName, Boolean.parseBoolean(isCompleted));
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }
    @Override
    public String toString() {
        String output = "[D]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + this.taskName + " (By: " + this.taskTime + ")";
        return output;
    }

    @Override
    public String toFileFormat() {
        String output = "deadline | ";
        if (completed) {
            output += "true";
        } else {
            output += "false";
        }
        output += " | " + this.taskName + " | " + this.taskDesc;
        return output;
    }
}
