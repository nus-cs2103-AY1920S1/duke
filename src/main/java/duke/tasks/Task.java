/**
 * A child class of Object which contains the description of the Task and whether the Task has been done.
 */
package duke.tasks;
import duke.exceptions.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * This method changes the task to be done when the task has yet to be done.
     */
    public void markAsDone() throws DukeException {
        if (this.isDone == true) {
            throw new DukeException("This task has already been done!");
        } else {
            this.isDone = true;
        }
    }

    /**
     * This method records the loaded task as done.
     */
    public void recordDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * This method returns an empty String as it will be overridden in its child classes for more specific format of
     * each type of task.
     */
    public String format() {
        return "";
    }
}
