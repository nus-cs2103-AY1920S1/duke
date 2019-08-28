package duke.task;

class TaskStub extends Task {

    TaskStub() {
        super("a stub", "01/08/2019 0000");
        this.taskType = TaskType.TODO;
    }

    TaskStub(String description, String timing) {
        super(description, timing);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String getStatusText() {
        return String.format("[Stub][%s] %s", getStatusIcon(), this.description);
    }
}
