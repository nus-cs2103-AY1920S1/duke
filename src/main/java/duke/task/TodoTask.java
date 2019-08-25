package duke.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
        this.taskType = TaskType.todo;
    }

    public String getStatusText() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
}
