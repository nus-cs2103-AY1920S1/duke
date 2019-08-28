public class Todo extends Task {

    /***
     * Class constructor.
     * @param description Description of Task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Class constructor with completion specified.
     * @param description Description of task
     * @param isDone Boolean for whether task is completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Serialize into the following format for storage, E.g. "T | 0 | read book".
     */
    @Override
    public String serialize() {
        return String.format("T | %d | %s\n", isDone ? 1 : 0, description);
    }

    /**
     * Overridden toString method to display to user, E.g. "[T][X] read book".
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
