package duke.task;

/**
 * Represents a Task.
 */
public class Task {
    public String item;
    public boolean isDone;

    /**
     * Initialises a Task.
     * A Task consist of the task name and whether the task has been done.
     *
     * @param item name of the task.
     */
    // Constructor
    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    /**
     * Marks this Task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the task name as a string.
     *
     * @return name of the task.
     */
    @Override
    public String toString() {
        return this.item;
    }

    /**
     * Returns the task name as a string.
     *
     * @return name of the task.
     */
    // overridden method by Deadline, Event and Todo, define method so as not to make the Task class an abstract class.
    public String saveTask() {
        return this.item;
    }

}
