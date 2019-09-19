public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the tasks (done or not done)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done
     */
    public String markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:\n"
                + "       [\u2713] " + description);
        return "Nice! I've marked this task as done:\n  [\u2713] " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the format of the String to be saved in txt file
     */
    public abstract String taskSavedTextFormat();
}
