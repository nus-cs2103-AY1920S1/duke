import java.io.Serializable;
import java.util.Date;


/**
 * A Task class representing a task with a isDone component.
 */
abstract class Task implements Serializable {
    String description;
    private boolean isDone;
    private static final long serialVersionUID = 42L;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}

class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}

class Deadline extends Task {
    private Date deadline;

    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }
}

class Event extends Task {
    private Date timeframe;

    public Event(String description, Date timeframe) {
        super(description);
        this.timeframe = timeframe;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + timeframe + ")";
    }
}

class TaskBuilder {
    private String description;
    private Date deadline;
    private Date timeframe;
    private TaskType type;

    TaskBuilder type(TaskType t) {
        this.type = t;
        return this;
    }

    TaskBuilder description(String d) {
        description = d;
        return this;
    }

    TaskBuilder deadline(Date d) {
        deadline = d;
        return this;
    }

    TaskBuilder timeframe(Date t) {
        timeframe = t;
        return this;
    }

    Task build() {
        switch (type) {
            case TODO:
                return new ToDo(description);
            case EVENT:
                return new Event(description, timeframe);
            case DEADLINE:
                return new Deadline(description, deadline);
        }
        return null;
    }

}