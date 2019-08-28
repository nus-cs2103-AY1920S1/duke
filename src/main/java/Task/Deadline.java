package duke.task;

import duke.helper.DateTimeHelper;
import java.time.LocalDateTime;

public class Deadline extends Task {

    final String taskType = "[D]";
    protected LocalDateTime deadlineTime;


    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadlineTime = by;
    }

    public String getDescription() {
        return this.description + "|" + DateTimeHelper.formatOutput(this.deadlineTime);
    }

    public String getType() {
        return taskType;
    }

    @Override
    public String toString() {
        return taskType + super.getStatusIcon() + " " + super.toString()
                + " (by: " + DateTimeHelper.formatOutput(deadlineTime)  + ")";
    }
}
