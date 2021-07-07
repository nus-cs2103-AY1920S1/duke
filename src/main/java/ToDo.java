/**
 * ToDo task of Duke.
 */
public class ToDo extends Task {

    /**
     * ToDo constructor with description.
     * @param description description of tasks
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}