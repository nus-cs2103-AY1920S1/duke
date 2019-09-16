package duke.task;

import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;

import java.io.Serializable;
import java.util.UUID;

/**
 * Serializable abstract task.
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    private final UUID uuid;
    private final TaskType type;
    private String details;
    private TimeFrame timeFrame;
    private boolean isDone;
    private final boolean isRecurring;

    /**
     * Task constructor.
     * @param type corresponding task type
     * @param details task details
     * @param timeFrame time frame representing start and end of task
     * @param isDone is task completed
     * @param isRecurring is task recurring
     */
    public Task(TaskType type, String details, TimeFrame timeFrame, Boolean isDone, Boolean isRecurring) {
        if (type.task != getClass()) {
            System.out.println("FATAL: TaskType does not correspond to Task.");
            System.exit(1);
        }

        this.uuid = UUID.randomUUID();
        this.type = type;
        this.details = details;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
        this.isRecurring = isRecurring;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public TaskType getTaskType() {
        return this.type;
    }

    public TimeFrame getTimeFrame() { return this.timeFrame; }

    public boolean isRecurring() {
        return this.isRecurring;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean isDone() {
        return this.isDone;
    }

    private String getIsDoneIcon() {
        return isDone ? "\u2713" : "\u2718";
    }


    public String getDetails() {
        return details;
    }

    /**
     * Gets pretty task description of the task properties.
     * @return description of task
     */
    public final String getDescription() {
        String description = String.format("[%s][%s] %s",
                type.code,
                getIsDoneIcon(),
                details);

        if (timeFrame.getDescription().equals("")) {
            return description;
        } else {
            return String.format("%s (%s)", description, timeFrame.getDescription());
        }
    }
}
