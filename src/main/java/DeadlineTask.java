import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Represents a deadline task. A deadline task is represented by a name and a time.
 */
public class DeadlineTask extends Task {
    String taskName;
    String taskTime;

    /**
     * Constructs a Deadline Task which is not completed.
     *
     * @param task a string that defines the deadline task. a deadline task requires a /by to split it's name and time.
     */
    public DeadlineTask(String task) {
        super(task, "D");
        assert task != null || !task.equals("") : "task should not be null";
        String[] taskSplit = task.split("/by");
        if (taskSplit.length < 2) {
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

    /**
     * Constructs a Deadline task.
     *
     * @param isCompleted Whether or not the task is completed yet.
     * @param taskName    The name of the task.
     * @param taskTime    The Time of the task represented by a string.
     */
    public DeadlineTask(String isCompleted, String taskName, String taskTime) {
        super(taskName, Boolean.parseBoolean(isCompleted), "D");
        assert taskName != null || !taskName.equals("") : "TaskName should not be empty";
        assert taskTime != null || !taskTime.equals("") : "taskTime should not be empty";
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    /**
     * Returns a string which represents this task.
     *
     * @return a String detailing the task's name and time.
     */
    @Override
    public String toString() {
        String output = super.toString() + " (By: " + this.taskTime + ")";
        return output;
    }

    /**
     * returns a string that is used to store it in the save file.
     *
     * @return a string specifically formatted for storage.
     */
    @Override
    public String toFileFormat() {
        String output = "deadline | ";
        if (completed) {
            output += "true";
        } else {
            output += "false";
        }
        output += " | " + this.taskName + " | " + this.taskTime;
        return output;
    }
}
