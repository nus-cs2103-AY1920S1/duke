package dose.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    /**
     * Snoozes ("postpones") the given task.
     * Not applicable to TodoTasks.
     */
    @Override
    public void snooze() {
        // do nothing
    }
}
