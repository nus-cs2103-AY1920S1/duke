/**
 * A Task class representing a task with a isDone component.
 */
abstract class Task {
    String description;
    private boolean isDone;

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
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }
}

class Event extends Task {
    private String timeframe;

    public Event(String description, String timeframe) {
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
    private String deadline;
    private String timeframe;
    private TaskType type;

    public TaskBuilder type(TaskType t) {
        this.type = t;
        return this;
    }

    public TaskBuilder description(String d) {
        description = d;
        return this;
    }

    public TaskBuilder deadline(String d) {
        deadline = d;
        return this;
    }

    public TaskBuilder timeframe(String t) {
        timeframe = t;
        return this;
    }

    public Task build() {
        switch (type) {
            case TODO:
                return new ToDo(description);
            case EVENT:
                return new Event(description, timeframe);
            case DEADLINE:
                return new Deadline(description,deadline);
        }
        return null;
    }

}