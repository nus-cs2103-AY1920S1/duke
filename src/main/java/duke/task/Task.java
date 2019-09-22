package duke.task;

import duke.task.tasks.entities.TimeFrame;
import error.task.TaskModificationException;

import java.io.Serializable;
import java.util.UUID;

/**
 * Serializable abstract class to represent a task that is to be completed by the user. Its base attributes includes
 * 1. a UUID to uniquely identify each task
 * 2. a unique character code to represent each type of task
 * 3. a details String to serve as a descriptor for each task
 * 4. a TimeFrame within which the task is to be completed
 * 5. a boolean flag to represent if the task is completed.
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private static String TASK_IS_DONE_ICON = "✓";
    private static String TASK_NOT_DONE_ICON = "✘";

    private final UUID uuid;
    private final char uniqueCharCode;
    private String details;
    private TimeFrame timeFrame;
    private boolean isDone;

    public Task(UUID uuid, char uniqueCharCode, String details, TimeFrame timeFrame, boolean isDone) {
        this.uuid = uuid;
        this.uniqueCharCode = uniqueCharCode;
        this.details = details;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
    }

    public final UUID getUuid() {
        return this.uuid;
    }

    public final char getUniqueCharCode() {
        return this.uniqueCharCode;
    }

    public String getTaskDetails() {
        return details;
    }

    public void setTaskDetails(String details) {
        this.details = details;
    }

    public TimeFrame getTaskTimeFrame() {
        return this.timeFrame;
    }

    public void setTaskTimeFrame(TimeFrame timeFrame) throws TaskModificationException {
        if (this.isTimeFrameCompatible(timeFrame)) {
            this.timeFrame = timeFrame;
            return;
        }

        throw new TaskModificationException("Time frame is incompatible.");
    }

    public boolean isTaskDone() {
        return this.isDone;
    }

    public void setTaskAsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a nicely formatted description of the task that reflects all of its base attributes including:
     * 1. its unique character code
     * 2. whether it is done
     * 3. its details
     * 4. (optional) a description of its TimeFrame if it exists
     * @return the nicely formatted description of the task as a string.
     */
    public final String getTaskDescription() {
        String taskDescription = String.format("[%s][%s] %s",
                this.uniqueCharCode,
                this.isDone ? TASK_IS_DONE_ICON : TASK_NOT_DONE_ICON,
                this.details);

        if (!this.timeFrame.hasDescription()) {
            return taskDescription;
        }

        return String.format("%s (%s)", taskDescription, timeFrame.getDescription());
    }

    /**
     * Returns true as long as two tasks have the same UUID.
     * @param o object to be compared to the Task instance.
     * @return true if the two tasks are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).getUuid().equals(this.uuid);
        } else {
            return false;
        }
    }

    /**
     * Returns a deep copy of the task instance other than its UUID which is immutable regardless.
     * @return a deep copy of the task instance.
     * @throws CloneNotSupportedException if cloning of the task instance fails.
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        Task clone = (Task) super.clone();
        clone.timeFrame = new TimeFrame(timeFrame.getStart(), timeFrame.getEnd());
        return clone;
    }

    /**
     * Method used by task to check if a TimeFrame is compatible before any calls to setTimeFrame is carried out.
     * A task's TimeFrame should always follow a fixed pattern. For instance, a task to be completed at a particular
     * time should have the same start and end times, a task to be completed after a certain time should have its start
     * time as null.
     * @param timeframe new TimeFrame instance to update the task with.
     * @return true if the TimeFrame instance is compatible.
     */
    public abstract boolean isTimeFrameCompatible(TimeFrame timeframe);
}
