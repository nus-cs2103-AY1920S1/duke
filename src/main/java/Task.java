/**
 * This is a class for tasks.
 * @author Choong Yong Xin
 */

public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of tasks.
     *
     * @return 1 if task is done, else 0.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    /**
     * Changes boolean isDone to true if task is done.
     *
     */
    void markAsDone() {
        isDone = true;
    }

    /**
     * Returns string for task display.
     *
     * @return Display string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns string for file writing.
     *
     * @return String to be saved.
     */
    public String stringForAppend() {
        return description;
    }
}
