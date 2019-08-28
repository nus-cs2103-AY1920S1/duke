/**
 * Encapsulates a to-do task.
 * A subclass of Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Overridden toString method. Converts a todo object into string form to be used
     * in to-do list display.
     * @return string representation of a task on the to-do list
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overridden toTextFileString method. Converts a deadline object into string form to be used
     * in storage text file.
     * @return string representation of a task on the storage text file
     */
    @Override
    public String toTextFileString() {
        return "T|" + super.toTextFileString();
    }
}
