package duke.task;

import duke.main.Parser;

import java.util.Date;

/**
 * Deadline is a task that has to be completed by a certain date.
 */
public class Deadline extends Task {
    private Date deadlineBy;

    /**
     * Creates a new Deadline with a description and Date of deadline.
     *
     * @param description The task's description.
     * @param deadlineBy The Date of the deadline.
     */
    public Deadline(String description, Date deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Returns a string of a Deadline that can contain
     * its type, completion status, description, time and PriorityLevel.
     *
     * @return A string that contains information about anDeadline.
     */
    public String saveInfo() {
        return "deadline" + " " + description + " /by " + Parser.inputDateFormat.format(deadlineBy)
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    /**
     * Returns a string containing full information of the Deadline.
     * This includes type, completion status, description, time
     * and PriorityLevel.
     *
     * @return A string representation of this Deadline.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[D][");
            sb.append(Task.DONE_SYMBOL);
            sb.append("] ");
        } else {
            sb.append("[D][");
            sb.append(Task.NOT_DONE_SYMBOL);
            sb.append("] ");
        }
        sb.append(description);
        sb.append(" (");
        sb.append(Parser.outputDateFormat.format(deadlineBy));
        sb.append(")");
        sb.append(" ");
        sb.append(priority.toString());
        return sb.toString();
    }
}
