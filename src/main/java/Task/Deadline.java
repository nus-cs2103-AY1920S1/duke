package duke.task;

import duke.helper.DateTimeHelper;
import java.time.LocalDateTime;

public class Deadline extends Task {

    final String TASK_TYPE = "[D]";
    protected LocalDateTime deadlineTime;


    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadlineTime = by;
    }

    public String getDescription(){
        return this.description + "|" + DateTimeHelper.formatOutput(this.deadlineTime);
    }

    public String getType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (by: " + DateTimeHelper.formatOutput(deadlineTime)  + ")";
    }
}
