package seedu.duke.tasks;

public class TodoTask extends Task {
    private static final long serialVersionUID = 208976467902347L;

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
