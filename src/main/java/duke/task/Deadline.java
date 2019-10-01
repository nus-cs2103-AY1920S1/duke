package duke.task;

import duke.datetime.DateTime;

public class Deadline extends Task {
    private DateTime deadlineTime;
    private static final String ABBREV_TASK = "D";

    public Deadline(String description, DateTime deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toSaveFormat() {
        return ABBREV_TASK + " | " + taskIsDoneState + " | " + description + " | " + deadlineTime.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}