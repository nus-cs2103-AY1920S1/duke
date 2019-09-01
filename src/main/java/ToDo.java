/**
 * Represents a task to be completed
 * Contains a description of the task
 */


public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
        this.type = "T";
        this.description = description.substring(5);
    }

    @Override
    public String toString() {
        String output = "[T][" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}
