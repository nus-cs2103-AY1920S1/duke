package duke.task;

public class Task {
    protected String description;
    protected boolean done;

    /**
     * Constructor for Task object with default done status
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Constructor for Task object with certain done status
     * @param description description of the task
     * @param done status of the task
     */
    protected Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Returns the boolean status of a task
     * @return the boolean status of a task
     */
    public boolean isCompleted() {
        return done;
    }

    /**
     * Changes the status of a task to 'completed' by returning new object
     * @return a new task with 'completed' status
     */
    public Task changeToCompletedStatus() {
        return new Task(this.description, true);
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns formatted form of the task to be stored in inside a text file
     * @return formatted form of the task to be stored in inside a text file
     */
    public String toIndicationInsideFile() {
        String s = "T | ";

        if(done) {
            s = s + "1 | ";
        } else {
            s = s + "0 | ";
        }

        return s + description;
    }
}