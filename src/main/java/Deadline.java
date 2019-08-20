public class Deadline extends Task {


    public Deadline(String taskName, String by) {
        super(taskName);
        this.prefix = "[D]";
        this.suffix = "(by: " + by + ")";
    }

    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName + " " + suffix;
    }
}
