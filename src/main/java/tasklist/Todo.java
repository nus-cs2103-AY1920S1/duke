package tasklist;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents the Todo type of task.
 */

public class Todo extends Task {

    public Todo(String description, boolean completionStatus) {
        super(description,completionStatus);
        taskType = new SimpleStringProperty("Todo");
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
