package duke.task;

import duke.time.DateTime;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.parseDateTime(deadlineBy) + ")";
    }

    /**
     * Converts the Deadline object to a String representing it for storage purposes.
     * @return
     */
    @Override
    public String toStorage() {
        int isDoneInt = isDone ? 1 : 0;
        return String.format("%d | D | %s | %s", isDoneInt, description, deadlineBy);
    }
}
