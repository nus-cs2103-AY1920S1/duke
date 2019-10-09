package duke.task;

/**
 * Represents a Task to do that contains only a description.
 */
public class ToDoTask extends Task {
    /**
     * Creates a ToDoTask.
     *
     * @param description The description of the Task, as inputted by the user.
     */
    public ToDoTask(String description) {
        super(description);
    }
    
    /**
     * Returns a default formatted String for writing data to the user's hard drive.
     * ToDoTask String includes tag identifying this as a ToDoTask, in addition to the Task default formatted String.
     *
     * @return Returns a String.
     */
    @Override
    public String formattedString() {
        return "T | " + super.formattedString() + "\n";
    }
    
    /**
     * Returns a default String for printing to the user's console.
     * Default String contains completion status and description of this Task.
     *
     * @return Returns a String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
