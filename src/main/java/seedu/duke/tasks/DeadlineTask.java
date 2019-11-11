package seedu.duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DeadlineTask extends Task {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter targetFormatter = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);

    private String timeDescription;

    /**
     * Constructor for <code>DeadlineTask</code> that represents one of the possible task types.
     * By default, the task will not be marked as done upon initialization.
     *
     * @param taskID          ID of the task
     * @param isDone          A boolean that indicates whether a <code>DeadlineTask</code> task has been done or not.
     * @param createdAt       Creation time
     * @param updatedAt       Last update time
     * @param description     Description of the <code>DeadlineTask</code> task.
     * @param timeDescription An information that specifies the deadline of the task.
     */
    DeadlineTask(long taskID, boolean isDone, Date createdAt, Date updatedAt,
                 String description, String timeDescription) {

        super(taskID, TaskType.DEADLINE, isDone, createdAt, updatedAt, description);
        setTimeDescription(timeDescription);
    }

    /**
     * Convenient constructor for <code>DeadlineTask</code> that represents one of the possible task types.
     *
     * @param description     Description of the <code>DeadlineTask</code> task.
     * @param timeDescription An information that specifies the deadline of the task.
     */
    public DeadlineTask(String description, String timeDescription) {
        super(TaskType.DEADLINE, description);
        setTimeDescription(timeDescription);
    }

    @Override
    public boolean equals(Task other) {
        if (!super.equals(other)) {
            return false;
        }
        DeadlineTask otherDeadlineTask = (DeadlineTask) other;
        return timeDescription.equals(otherDeadlineTask.timeDescription);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + timeDescription + ")";
    }

    @Override
    String getDataRepresentation() {
        return super.getDataRepresentation() + " | " + timeDescription;
    }

    private void setTimeDescription(String timeDescription) {
        if (timeDescription.matches("[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}")) {
            try {
                this.timeDescription = LocalDateTime.parse(timeDescription, formatter).format(targetFormatter);
            } catch (Exception e) {
                this.timeDescription = timeDescription;
            }
        } else {
            this.timeDescription = timeDescription;
        }
    }
}
