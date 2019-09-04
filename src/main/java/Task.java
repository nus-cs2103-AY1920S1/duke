public class Task {

    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task
     *
     * @param description description of the Task.
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return the appropriate status icon for whether the task is done or not.
     *
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDecription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}