/**
 * Represents a todo-type task which inherits from Task.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class ToDo extends Task {

    /**
     * Creates a todo task with description.
     * @param description Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo object for UI.
     * @return A string representation of the todo object for UI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo object for Storage.
     * @return A string representation of the todo object for Storage.
     */
    @Override
    public String saveString() {
        int done = this.getDone() ? 1 : 0;
        return "T" + super.saveString() + "\n";
    }
}
