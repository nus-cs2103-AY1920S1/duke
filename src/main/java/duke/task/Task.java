package duke.task;

import duke.exception.DukeTaskException;

public abstract class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructs a Task with description.
     *
     * @param description the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks that the Task has been done.
     *
     * @throws DukeTaskException if the Task has already been done and the user wants it to be done again.
     */
    public void doTask() throws DukeTaskException {
        if (this.isDone) {
            throw new DukeTaskException("The task specified has already been done.");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Marks the Task as done regardless of whether it has been done. This method is called when
     * the text file stored on the hard disk is being written to the TaskList.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a unicode representation of whether the Task has been done.
     *
     * @return the String representation of the unicode.
     */
    public String getStatusIcon() {
        return this.isDone ? "[\u2713]" : "[\u2718]";  //represents tick and cross
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
