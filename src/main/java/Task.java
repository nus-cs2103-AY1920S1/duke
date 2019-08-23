public abstract class Task {
    public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy HHmm";
    public static final String OUTPUT_DATE_FORMAT = "dd MMMM yyyy hh:mma";

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract String getInitial();
    abstract String getAdditionalMessage();

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getInitial() + "]" + "[" + getStatusIcon() + "] " + description + " " + getAdditionalMessage();
    }
}