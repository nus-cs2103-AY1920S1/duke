package czkay.duke.model.task;

/**
 * Represents the todo task given by the user.
 */
public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", (isDone ? "\u2713" : "\u2717"), taskDescription);
    }

}
