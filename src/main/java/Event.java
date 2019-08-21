public class Event extends Task {

    public Event(String taskName, String at) {
        super(taskName);
        this.prefix = "[E]";
        this.details = "(at: " + at + ")";
    }



    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName + " " + details;
    }
}
