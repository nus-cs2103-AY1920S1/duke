public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //get methods
    public String getDescription() {
        return this.description;
    }

    //set methods
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String printTask = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return printTask;
    }
}