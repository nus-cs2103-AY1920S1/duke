/**
 * Represents a task to be completed. A <code>Todo</code> object corresponds to a task
 * represented by a name, type and date.
 */
public class Todo extends Task {

    /**
     * Type of task.
     */
    String type;

    /**
     * Class constructor assigning name and type to the object.
     * @param task_name Name of todo task.
     */
    public Todo(String task_name) {
        super(task_name);
        type = "todo";
    }

    /**
     * This method is used to get the type of the todo task.
     * @return String This returns the type of the todo task
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * This method is used to get the string representing the todo task.
     * @return String This returns the string representing the todo task
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
