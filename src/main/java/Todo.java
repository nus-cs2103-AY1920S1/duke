/**
 * Represents a type of task
 */
public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    /**
     * Returns user-readable string
     * @return user-readable string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns formatted string used while saving
     * @return formatted string
     */
    @Override
    public String saveFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
