/**
 * A subclass of Task that categorized itself as a regular thing that need to be done.
 *
 * @param description description of the Todo (Task).
 */
public class Todo extends Task {
    // to manage incoming to-do list
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}