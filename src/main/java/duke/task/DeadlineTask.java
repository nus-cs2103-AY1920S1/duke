package duke.task;

import java.time.LocalDateTime;

import duke.DukeInvalidArgumentException;

public class DeadlineTask extends Task {
    LocalDateTime deadlineDate;

    public DeadlineTask(String description, String deadlineDate) throws DukeInvalidArgumentException {
        super(description, deadlineDate);
        this.taskType = TaskType.deadline;
        initDates(deadlineDate);
    }

    @Override
    public String getStatusText() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                this.description,
                this.getFormattedTiming());
    }

    private String getFormattedTiming() {
        return TaskUtil.getDisplayTime(deadlineDate);
    }

    private void initDates(String deadlineDate) throws DukeInvalidArgumentException {
        if (deadlineDate == null) {
            throw new DukeInvalidArgumentException(
                    "Null reference provided to task constructor",
                    "\u2639 OOPS!!! The timing for this task cannot be empty!");
        }

        this.deadlineDate = TaskUtil.getDateFromString(deadlineDate);
    }
}
