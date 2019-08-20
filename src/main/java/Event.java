public class Event extends Task {
    private String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.prefix = "[E]";
        this.at = "(at: " + at + ")";
    }

    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName + " " + at;
    }
}
