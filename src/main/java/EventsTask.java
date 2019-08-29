import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class EventsTask extends Task {
    String taskName;
    String taskTime;

    public EventsTask(String task) {
        super(task);
        String[] taskSplit = task.split("/at");
        if(taskSplit.length < 2) {
            throw new EmptyDescriptionDukeException("event", "/at");
        } else if (taskSplit[0].equals("")) {
            throw new EmptyDescriptionDukeException("event");
        }
        taskName = taskSplit[0].trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        taskTime = taskSplit[1];

        try {
            Date time = formatter.parse(taskTime);
            formatter = new SimpleDateFormat("dd MMMM yyyy, ha");
            taskTime = formatter.format(time);
        } catch (ParseException err) {
            throw new InvalidTimeDukeException();
        }
    }
    public EventsTask(String isCompleted,String taskName, String taskTime) {
        super(taskName, Boolean.parseBoolean(isCompleted));
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        String output = "[E]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + this.taskName + " (At: " + this.taskTime + ")";
        return output;
    }

    @Override
    public String toFileFormat() {
        String output = "event| ";
        if (completed) {
            output += "true";
        } else {
            output += "false";
        }
        output += " | " + this.taskName + " | " + this.taskTime;
        return output;
    }
}