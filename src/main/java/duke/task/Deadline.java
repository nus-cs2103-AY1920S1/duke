package duke.task;

import java.util.Date;

/**
 * Deadline is a task that has to be completed by a certain date.
 */
public class Deadline extends Task{
    private Date deadlineBy;

    public Deadline(String taskDetails, Date deadlineBy) {
        super(taskDetails);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    public String saveInfo() {
        return "deadline" + " " + taskDetails + " /by " + TaskList.inputDateFormat.format(deadlineBy) + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[D][✓] ");
        } else {
            sb.append("[D][✗] ");
        }
        sb.append(taskDetails);
        sb.append (" (");
        sb.append(TaskList.outputDateFormat.format(deadlineBy));
        sb.append(")");
        return sb.toString();
    }
}
