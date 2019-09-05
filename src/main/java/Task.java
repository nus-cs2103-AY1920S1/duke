/**
 * The Task class is used to represent Tasks and acts as the 
 * superclass to the Event, Deadline and Todos classes. 
 */
public class Task {
    String task;
    Boolean complete;

    String done = "[✓] ";
    String pending = "[✗] ";

    /**
     * Creates a new Task object to be used within Duke.
     * @param task Description of the task to be created.
     */
    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    /**
     * Creates a new Task object to be used within Duke with the option
     * to predetermine whether it has been completed or not.  
     * @param task Description of the task to be created.
     * @param isPending Boolean to state whether the task been completed.
     */
    public Task(String task, boolean isPending) {
        this.task = task;
        this.complete = isPending;
    }

    /**
     * Returns the string representation of a Task for the purpose of writing
     * back onto the file.
     * @return String representation of a task.
     */
    public String toStringForFile() {
        return "wrong because of this";
    }

    /**
     * Returns the string representation of a Task.
     */
    @Override
    public String toString() {
        if (this.complete) {
            return done + task;
        } else {
            return pending + task;
        }
    }

    /**
     * Sets the status of a particular task to completed.
     */
    public void markAsDone() {
        this.complete = true;
    }

}