/** This class handles the todo class of events. */
public class ToDo extends Task {
    /** Constructor. */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
