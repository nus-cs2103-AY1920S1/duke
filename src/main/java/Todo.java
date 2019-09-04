package duke.task;

/**
 * Child class of a Task class that respresents a Task of type todo
 * that only has a desciption.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with specified desciption.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
        super.typeOfTask = "T";
    }

    public String toString() {
        return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
