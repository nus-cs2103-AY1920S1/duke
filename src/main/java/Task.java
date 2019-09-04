public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       [\u2713] " + description + "\n"
                + "    ____________________________________________________________\n");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String taskSavedTextFormat();
}
