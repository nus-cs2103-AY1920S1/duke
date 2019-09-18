package duke.task;

public class TaskImpl extends Task {
    public TaskImpl(String description) {
        super(description);
    }

    @Override
    String getTaskType() {
        return "type";
    }
}
