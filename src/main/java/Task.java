public abstract class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets string formatted to be saved.
     * @return String to be written into save file.
     */
    public abstract String getSaveString();
}