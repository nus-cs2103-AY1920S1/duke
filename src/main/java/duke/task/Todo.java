package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representing the type of task.
     *
     * @return string representing type of task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns string description of the task.
     *
     * @return string description of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
