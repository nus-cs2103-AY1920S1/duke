/**
 * Represents the todo task given by the user.
 */
class Todo extends Task {

    Todo(String taskDescription) {
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
