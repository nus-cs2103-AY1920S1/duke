package seedu.duke.tasks;

import java.util.Date;

public class EventTask extends Task {
    private String timeDescription;

    /**
     * Constructor for <code>EventTask</code> that represents one of the possible task types.
     * By default, the task will not be marked as done upon initialization.
     *
     * @param taskID          ID of the task
     * @param description     Description of the <code>EventTask</code> task.
     * @param timeDescription An information regarding the event.
     * @param isDone          A boolean that indicates whether an <code>EventTask</code> task has been done or not.
     * @param createdAt       Creation time
     * @param updatedAt       Last update time
     */
    EventTask(long taskID, boolean isDone, Date createdAt, Date updatedAt,
              String description, String timeDescription) {

        super(taskID, TaskType.EVENT, isDone, createdAt, updatedAt, description);
        this.timeDescription = timeDescription;
    }

    /**
     * Convenient constructor for <code>EventTask</code> that represents one of the possible task types.
     *
     * @param description     Description of the <code>EventTask</code> task.
     * @param timeDescription An information regarding the event.
     */
    public EventTask(String description, String timeDescription) {
        super(TaskType.EVENT, description);
        this.timeDescription = timeDescription;
    }

    @Override
    public boolean equals(Task other) {
        if (!super.equals(other)) {
            return false;
        }
        EventTask otherEventTask = (EventTask) other;
        return timeDescription.equals(otherEventTask.timeDescription);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + timeDescription + ")";
    }

    @Override
    String getDataRepresentation() {
        return super.getDataRepresentation() + " | " + timeDescription;
    }
}
