package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    /**
     * Snoozes ("postpones") the given task.
     * Not applicable to tasks of TODO type.
     */
    @Override
    public void snooze() {
        // do nothing
    }
}
