package duke.task;


public class TaskStub extends Task {

    public TaskStub() {
        super("a stub", "01/08/2019 0000");
        this.taskType = TaskType.TODO;
    }

    public TaskStub(String description, String timing) {
        super(description, timing);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String getStatusText() {
        return String.format("[Stub][%s] %s", getStatusIcon(), this.description);
    }
}
