package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo Task.
     *
     * @param description description of the task to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the task into a StringBuilder object to write into storage.
     *
     * @return a StringBuilder object for file writing
     */
    @Override
    public StringBuilder saveData() {
        return new StringBuilder("T|").append(super.saveData());
    }

    /**
     * Returns a string representation of this task.
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}