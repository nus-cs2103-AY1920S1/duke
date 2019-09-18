// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

/**
 * Class representation of a Todo in the list.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with a description.
     * 
     * @param description A string description of this Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    /**
     * Returns a String representation of this Todo
     * 
     * @return A string representation of this Todo
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}