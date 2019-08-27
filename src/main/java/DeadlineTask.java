/**
 * A class representing a <code>Task</code> with a deadline. Contains date-time information for when the <code>Task</code>
 * should be completed by.
 */
public class DeadlineTask extends Task {
    private final DukeDateTime deadlineTime;

    /**
     * Creates an instance of a <code>DeadlineTask</code>. The completion status is set to <code>false</code> by default.
     * 
     * @param description The description of this <code>Task</code>
     * @param deadlineTime The date-time by which this <code>Task</code> should be completed
     */
    public DeadlineTask(String description, DukeDateTime deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
        this.isDone = false;
    }

    /**
     * Creates an instance of a <code>DeadlineTask</code>. Allows the caller to set its completion status.
     * 
     * @param description The description of this <code>Task</code>
     * @param deadlineTime The date-time by which this <code>Task</code> should be completed
     * @param isDone The completion status of the <code>Task</code>
     */
    public DeadlineTask(String description, DukeDateTime deadlineTime, boolean isDone) {
        this.description = description;
        this.deadlineTime = deadlineTime;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new DeadlineTask(description, deadlineTime, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new DeadlineTask(description, deadlineTime, false);
    }

    /**
     * @return The <code>String</code> representation of this <code>Task</code>, containing the type of <code>Task</code>,
     * completion status and description
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TO_STRING_FORMAT, 'D', this.getStatusIcon(), this.description));
        sb.append(String.format(" (by %s)", deadlineTime.toString()));
        return sb.toString();
    }
}
