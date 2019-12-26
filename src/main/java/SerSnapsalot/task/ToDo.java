package SerSnapsalot.task;

/**
 * Represents a task to be completed.
 * Contains a description of the task.
 */

public class ToDo extends Task {

    public ToDo(String description) {
        super(description.substring(1));
        this.type = "T";
    }

    @Override
    public String toString() {
        String output = "[T][" + this.getStatusIcon() + "] " + this.description + "\n";
        return output;
    }
}
