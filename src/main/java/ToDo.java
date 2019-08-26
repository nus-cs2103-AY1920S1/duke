public class ToDo extends Task {

    /**
     * Represents a to-do, which is a type of task.
     *
     * @param description The string description of the to-do created.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
