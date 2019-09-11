package tasklist;

/**
 * Represents the Todo type of task.
 */

public class Todo extends Task {

    public Todo(String description, boolean completionStatus) {
        super(description,completionStatus);
    }

    @Override
    public String getOverallStatus() {
        return "[T]" + getCurrentStatus() + description.getValue();
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone.getValue() ? 1 : 0;
        return "todo [" + myInt + "]" + description.getValue();
    }
}
