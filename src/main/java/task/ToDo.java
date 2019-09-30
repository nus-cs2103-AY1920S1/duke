package task;

public class ToDo extends Task {
    /**
     * Constructor for ToDo objects.
     * @param description String description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * toString() returns the String representation of this ToDo task.
     * @return String.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }

}
