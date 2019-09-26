package duke.models;

public class Planner {

    protected String description;
    protected boolean isDone;

    public Planner(String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Method to mark the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }
}
