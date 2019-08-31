/**
 * Inherits from the Task class and contains methods for ToDo category of tasks.
 */
public class ToDo extends Task {
    /**
     * Creates a todo event with description and status.
     * @param description The description of the task.
     * @param b the state of the task, i.e. whether it is done or has to be done.
     */

    public ToDo(String description, boolean b) {
        super(description);
        this.isDone = b;
    }

    /**
     * Creates the task with only description and default state of the task being not done.
     * @param description the description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     *Method to display information in the required format.
     * @return The task description with the prefix letter T indicating the type of task which corresponds to Todo here.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
