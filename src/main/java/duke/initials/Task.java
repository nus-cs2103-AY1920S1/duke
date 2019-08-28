package duke.initials;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the isDone boolean on tasks to true, this will affect the icon seen in the txt file
     * as well as on the list within the program
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getData();

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}