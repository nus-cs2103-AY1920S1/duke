package duke.task;

import duke.exception.DukeException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    /**
     * the Deadline(date) of task stored as a string.
     */
    protected String deadline;

    /**
     * Constructor for Deadline which takes in a whole command as a String.
     * It splits the command by the "/by" sequence of characters, then calls the
     * overloaded constructor which takes in a description and a Deadline.
     *
     * @param message the command to create a new Deadline task.
     * @throws DukeException if there is an error trying to create the Deadline task.
     */
    public Deadline(String message) throws DukeException {
        this(message.split("/by", 2)[0],
                (message.split("/by", 2).length > 1
                        ? message.split("/by", 2)[1]
                        : ""));
        this.taskType = "D";
    }


    /**
     * Constructor for Deadline that takes in a description and a Deadline(as a string).
     * It parses the Deadline using the parseDate method inherited from the Task parent class.
     * It throws a DukeException when either the description or the date field is empty.
     *
     * @param description The description of the Deadline
     * @param deadline    The date of the Deadline(as a string)
     * @throws DukeException When there is an error in the format of the Deadline
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = parseDate(deadline);
        if (this.getTaskDescription().equals("")) {
            throw new DukeException(":( OOPS!!! The description of a Deadline cannot be empty.");
        }
        if (this.getDeadline().equals("")) {
            throw new DukeException(":( OOPS!!! The date field of a Deadline cannot be empty.");
        }
        this.taskType = "D";
    }

    /**
     * Constructor for Deadline which allows the specification of the isDone boolean.
     * This is used mainly when we parse the Deadline from storage, as it may be marked as done, but not
     * removed from the TaskList yet. It calls the two-argument constructor, then assigns isDone.
     *
     * @param description The description of the Deadline
     * @param isDone      Whether the Deadline is marked as done
     * @param deadline    The date of the Deadline stored as a string
     * @throws DukeException Error if the format of the Deadline is incorrect
     */
    public Deadline(String description, boolean isDone, String deadline) throws DukeException {
        this(description, deadline);
        this.isDone = isDone;
    }

    /**
     * Returns the date Deadline of the Deadline task.
     *
     * @return Date Deadline of the Deadline (as a string)
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Marks a Deadline task as done.
     * Throws a DukeException if the Deadline is already marked as done.
     * We return the task so that we can get its updated status more easily.
     *
     * @return the Deadline that has been marked as done
     * @throws DukeException if there is an error marking the Deadline as done
     */
    public Task markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException(":( OOPS!!! The Deadline is already marked as done.");
        }
        Deadline completed = new Deadline(this.description, this.deadline);
        completed.isDone = true;
        return completed;
    }

    /**
     * Gets the Deadline status as a string.
     * The string returned is formatted as specified on the CS2103T website.
     *
     * @return the task status as a string.
     */
    public String getTaskStatus() {
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription()
                + "(by: " + this.getDeadline() + ")");
    }

    /**
     * Gets the Deadline status a string, formatted for storage in the .txt file.
     * The string returned is formatted for storage as specified on the CS2103T website.
     *
     * @return the task status as a string for storage.
     */
    public String getStoredTaskStatus() {
        return (this.getTaskType() + " | " + this.getStatusIcon() + " | " + this.getTaskDescription()
                + " | " + this.getDeadline());
    }
}

