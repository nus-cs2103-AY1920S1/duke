package duke.task;

import duke.exception.DukeException;

/**
 * A class that represents a Task that the user wishes to complete.
 */
public abstract class Task {

    /**
     * The description of the Task.
     */
    protected String description;

    /**
     * Represents whether the Task has been done.
     */
    protected Boolean done;

    /**
     * Constructs a Task with description.
     *
     * @param description the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Marks that the Task has been done.
     *
     * @throws DukeException if the Task has already been done and the user wants it
     * to be done again, the exception is thrown.
     */
    public void doTask() throws DukeException{
        if (this.done) {
            throw new DukeException("The task specified has already been done.");
        } else {
            this.done = true;
        }
    }

    /**
     * Marks the Task as done regardless of whether it has been done. This method is called when
     * the text file stored on the hard disk is being written to the TaskList.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * It represents a unicode representation of whether the Task has been done.
     *
     * @return the String representation of the unicode.
     */
    public String getStatusIcon() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    /**
     * An abstract method that formats the Task into a format more suited to be written to the text
     * file stored on the hard disk.
     *
     * @return the formatted string that represents the details of a Task.
     */
    public abstract String formatToWrite();

    @Override
    public String toString() {
       return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}
