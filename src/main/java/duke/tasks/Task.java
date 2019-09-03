package duke.tasks;

/** Parent class implementing the logic common to all Task types. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description String Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Will set the <code>isDone</code> boolean to true. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Getter method for the <code>isDone</code> boolean.
     *
     * @return boolean representing whether the task has been completed
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Getter method for the unicode String for either a cross or tick, depending
     * on the value of the boolean <code>isDone</code>.
     *
     * @return unicode String (either a tick or a cross)
     */
    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Generates a String representation of the Task.
     *
     * @return String representation of the Task
     */
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

    /**
     * Generates a String representation of the Task in a format
     * that is compatible for the Storage object to read and write.
     *
     * @return String representation of the Task (compatibility with Storage class)
     */
    public String getStorageFormat() {
        String doneStatusString = this.isDone ? "1" : "0";
        String storageString = doneStatusString + " | " + this.description;
        return storageString;
    }
}
