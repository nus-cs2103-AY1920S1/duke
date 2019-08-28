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
     * Get the status icon
     * @return tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set the isDone variable to true
     */
    public void done() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Form a formatted sentence to be saved into a txt file later
     * @return string
     */
    public String toStringFile() {
        return ((isDone) ? "1" : "0") + " | " + description;
    }
}
