public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String toEncodedString() {
        return String.format("T | %d | %s", this.isComplete ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        // Adds the type of the Task to the base toString() representation
        return String.format("[T]%s", super.toString());
    }
}
