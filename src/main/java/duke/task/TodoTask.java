package duke.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
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
