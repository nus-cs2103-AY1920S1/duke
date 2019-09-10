package duke.task;

import duke.tag.Tag;

/**
 * A Class that represents a Task in which the user wishes to complete.
 */
public class Task {
    public String todo;
    public boolean isCompleted;
    private Tag tag;

    /**
     * Constructs a Task which sets the default of isCompleted value to false.
     * @param todo The name of the task.
     */
    public Task(String todo) {
        this.todo = todo;
        this.isCompleted = false;
        this.tag = new Tag("");
    }

    /**
     * Constructs a Task based on the name and the isComplated value given.
     * @param todo The name of the task.
     * @param isCompleted Whether the task is Completed or not.
     */
    public Task(String todo, boolean isCompleted) {
        this.todo = todo;
        this.isCompleted = isCompleted;
    }

    /**
     * Pushes a new tag to the Task.
     * @param tag the tag for the task.
     */
    public void pushTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * Retrieves the tag.
     * @return the tag.
     */
    public Tag getTag() {
        return this.tag;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (isCompleted) {
            return "[Y] " + this.todo;
        } else {
            return "[N] " + this.todo;
        }
    }

    /**
     * Changes the task name to the new task name given.
     * @param newTaskName the new task name given.
     */
    public void editTaskName(String newTaskName) {
        this.todo =  newTaskName;
    }
}
