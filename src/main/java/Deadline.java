public class Deadline extends Task {

    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.prefix = "[D]";
        this.by = "(by: " + by + ")";
    }

    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName + " " + by;
    }
}
