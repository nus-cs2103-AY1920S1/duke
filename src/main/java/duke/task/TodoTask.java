package duke.task;

public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}
