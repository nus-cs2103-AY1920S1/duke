package seedu.duke.task;

public class Todo extends Task {
    public static final String INITIAL = "T";

    public Todo(String desc) {
        super(desc.trim());
    }

    @Override
    String getInitial() {
        return INITIAL;
    }
}
