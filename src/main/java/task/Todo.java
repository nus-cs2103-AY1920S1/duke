package task;

//@@author Parcly-Taxel
public class Todo extends Task {
    /**
     * Initialises a Todo from its description.
     */
    private Todo(String desc) {
        super(desc);
    }
    
    /**
     * Parses the given line and returns a Todo constructed from it.
     */
    public static Todo parse(String line) throws IllegalArgumentException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return new Todo(line);
    }
    
    /**
     * Returns a string representation of this Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
