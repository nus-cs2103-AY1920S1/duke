package duke.task;

import java.time.LocalDateTime;

import duke.DukeInvalidArgumentException;

public class DeadlineTask extends Task {
    LocalDateTime deadlineDate;

    public DeadlineTask(String description, String deadlineDate) throws DukeInvalidArgumentException {
        super(description, deadlineDate);
        this.taskType = TaskType.deadline;
        this.deadlineDate = TaskUtil.getDateFromString(deadlineDate);
    }

    public String getStatusText() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                this.description,
                this.getFormattedTiming());
    }

    private String getFormattedTiming() {
        return TaskUtil.getDisplayTime(deadlineDate);
    }
}
