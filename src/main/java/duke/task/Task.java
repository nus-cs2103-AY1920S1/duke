package duke.task;

import duke.task.tasks.entities.TaskType;
import duke.task.tasks.entities.TimeFrame;
import error.task.TaskCreationException;

import java.io.Serializable;

/***
 * <p>
 * Task interface to create new duke.task types.
 * Serializable to be written to storage.
 * </p>
 */
public abstract class Task implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    private TaskType type;
    private String details;
    private TimeFrame timeFrame;
    private boolean isDone;
    private final boolean isRecurring;

    public Task(TaskType type, String details, TimeFrame timeFrame, boolean isDone, boolean isRecurring) {
        if (type.task != getClass()) {
            System.out.println("FATAL: TaskType does not correspond to Task.");
            System.exit(1);
        }

        this.type = type;
        this.details = details;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
        this.isRecurring = isRecurring;
    }

    public TaskType getTaskType() {
        return this.type;
    }

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

    public final String getDescription() {
        String description = String.format("[%s][%s] %s",
                type.code,
                getIsDoneIcon(),
                details);

        if (timeFrame.getDescription().equals("")) {
            return description;
        } else {
            return String.format("%s, (%s)", description, timeFrame.getDescription());
        }
    }
}
