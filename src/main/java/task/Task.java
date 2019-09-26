package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.Task object. Default constructor.
     */
    public Task() {
    }

    /**
     * Creates a Task.Task object.
     *
     * @param description string input to be chopped up into different variables.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets the status icon.
     *
     * @return {@link String} tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone)
                ? "✓" //"\u2713"
                : "✘"; //"\u2718";
    }

    /**
     * Sets the isDone variable to true.
     */
    public void done() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted sentence to be saved into a txt file later.
     *
     * @return {@link String} Formatted sentence.
     */
    public String toStringFile() {
        return ((isDone) ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }
}
