package duke.task;

import java.time.LocalDateTime;

import duke.command.DukeInvalidArgumentException;

/**
 * Abstraction of a task representing a deadline by a certain date time.
 */
public class DeadlineTask extends Task {
    /** The LocalDateTime deadline of the dead line task. */
    private LocalDateTime deadlineDate;

    /**
     * Constructor of the deadline task.
     * Calls the generic task constructor and initializes its task type.
     * Also calls initDates for validating and setting its due date.
     *
     * @param description The task description string.
     * @param deadlineDate The input timing string.
     * @throws DukeInvalidArgumentException If any of the inputs are of invalid format.
     */
    public DeadlineTask(String description, String deadlineDate) throws DukeInvalidArgumentException {
        super(description, deadlineDate);
        this.taskType = TaskType.deadline;
        initDates(deadlineDate);
    }

    /**
     * Method retrieving task type specific status strings.
     *
     * @return The task type specific status string.
     */
    @Override
    public String getStatusText() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                this.description,
                this.getFormattedTiming());
    }

    /**
     * Private function for retrieving the formatted timing for display.
     *
     * @return The formatted timing string.
     */
    private String getFormattedTiming() {
        return TaskUtil.getDisplayTime(deadlineDate);
    }

    /**
     * Private function for validating and setting the deadline's due date.
     * Validates the timings according to the format defined
     * in TaskUtil, then sets the LocalDateTime instance
     * property.
     *
     * @param deadlineDate The input timing string.
     * @throws DukeInvalidArgumentException If the input timing format is invalid or null.
     */
    private void initDates(String deadlineDate) throws DukeInvalidArgumentException {
        if (deadlineDate == null) {
            throw new DukeInvalidArgumentException(
                    "Null reference provided to task constructor",
                    "\u2639 OOPS!!! The timing for this task cannot be empty!");
        }

        this.deadlineDate = TaskUtil.getDateFromString(deadlineDate);
    }
}
