package duke.task;

/**
 * Class representing a duke.task to be done, but not at any particular time.
 */
public class Todo extends Task {
    /**
     * Creates a Todo from its description.
     *
     * @param desc What is to be done.
     */
    private Todo(String desc) {
        super(desc);
    }
    
    /**
     * Parses the given line and returns a Todo constructed from it.
     *
     * @param line The parsed line.
     */
    public static Todo parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return new Todo(line);
    }
    
    /**
     * Returns a string representation of this Todo.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
