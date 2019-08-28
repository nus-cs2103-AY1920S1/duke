import java.util.Date;

/** Class to represent a task. */
abstract class Task {
    protected String name;
    protected boolean done;
    protected TaskType type;

    public Task(String name) {
        this.name = name;
    }

    // mark task as done
    public void markDone() {
        this.done = true;
    }

    // get task name
    public String getName() {
        return this.name;
    }

    // check if task is done
    public boolean isTaskDone() {
        return this.done;
    }

    // get task type
    public abstract TaskType getType();

    // get task date
    public abstract Date getDate();
}