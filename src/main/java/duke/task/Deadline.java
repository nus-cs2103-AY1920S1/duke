package duke.task;

import duke.main.Parser;

import java.util.Date;

/**
 * Deadline is a task that has to be completed by a certain date.
 */
public class Deadline extends Task {
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
        return "deadline" + " " + taskDetails + " /by " + Parser.inputDateFormat.format(deadlineBy)
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[D][\u2713] ");
        } else {
            sb.append("[D][\u2717] ");
        }
        sb.append(taskDetails);
        sb.append(" (");
        sb.append(Parser.outputDateFormat.format(deadlineBy));
        sb.append(")");
        sb.append(" ");
        sb.append(priority.toString());
        return sb.toString();
    }
}
