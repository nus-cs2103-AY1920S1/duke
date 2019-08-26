public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        // Adds the type of the Task to the base toString() representation
        return String.format("[T]%s", super.toString());
    }
}
