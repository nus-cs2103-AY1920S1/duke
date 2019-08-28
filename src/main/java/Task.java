public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon
     *
     * @return tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone)
                ? "\u2713"
                : "\u2718";
    }

    /**
     * Sets the isDone variable to true
     */
    public void done() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted sentence to be saved into a txt file later
     *
     * @return string: Formatted sentence
     */
    public String toStringFile() {
        return ((isDone) ? "1" : "0") + " | " + description;
    }
}
