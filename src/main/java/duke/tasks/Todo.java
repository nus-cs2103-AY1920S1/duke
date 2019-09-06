package duke.tasks;

/**
 * To do class that can be created by the user. Handles all Duke.tasks with To do.
 */
public class Todo extends Task {
    
    /**
     * Class constructor.
     *
     * @param description details of the respective task.
     */
    public Todo(String description) {
        super(description);
    }
    
    /**
     * Prints the to do task with the status and the description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
