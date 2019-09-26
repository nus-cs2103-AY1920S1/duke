package duke.models;

public class Expenses extends Planner {

    protected String on;

    public Expenses(String description, String on) {
        super(description);
        this.on = on;
    }

    /**
     * Method to mark the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[Expenses] " + description + " (on: " + on + ")";
    }
}
