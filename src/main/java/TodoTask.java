public class TodoTask extends Task {
    /**
     *  Constructs a <code>TodoTask</code> object with a given description.
     *  @param description <code>String</code> description of this <code>Task</code>.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     *  {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format("T | %d | %s", this.isComplete ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        // Adds the type of the Task to the base toString() representation
        return String.format("[T]%s", super.toString());
    }
}
