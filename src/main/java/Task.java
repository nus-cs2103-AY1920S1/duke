/**
 * Simulates a task for accessing and updating descriptions
 * and done statuses.
 * @author Fabian Chia Hup Peng
 */

public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task instance with the appropriate attributes.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of a task, with ticks representing done
     * and crosses representing undone.
     * @return The status icon of the task.
     */
    public String getStatusIcon(){
        if(isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Returns the task description in String format.
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of a task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Formats the task in suitable String representation for
     * writing to file.
     */
    public abstract String formatForFile();

    /**
     * Returns a string representation of a task.
     * @return The task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
