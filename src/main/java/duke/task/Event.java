package duke.task;

import duke.exception.DukeException;

/**
 * A representation of an Event task.
 */
public class Event extends Task {

    /**
     * The date of the event as a string.
     */
    protected String eventDate;

    /**
     * Constructor for the Event that takes in a message as a String.
     * It splits the command by the "/at" sequence, then calls the overloaded constructor
     * that takes in two arguments, the description as well as the eventDate.
     *
     * @param message the message that contains the details of the event
     * @throws DukeException thrown if there is an error creating the event
     */
    public Event(String message) throws DukeException {
        this(message.split("/at", 2)[0],
                (message.split("/at", 2).length > 1
                        ? message.split("/at", 2)[1]
                        : ""));
        this.taskType = "E";
    }

    /**
     * Constructor for Event that takes in a description String, and an eventDate String.
     * It calls parseDate() from the parent class to parse the eventDate string,
     * and throws a DukeException if either the event description or date field is empty.
     *
     * @param description the description of the event
     * @param eventDate   the date of the event stored as a String
     * @throws DukeException thrown if there is an error creating the Event.
     */
    public Event(String description, String eventDate) throws DukeException {
        super(description);
        this.eventDate = parseDate(eventDate);
        if (this.getTaskDescription().equals("")) {
            throw new DukeException(":( OOPS!!! The description of a event cannot be empty.");
        }
        if (this.getEventDate().equals("")) {
            throw new DukeException(":( OOPS!!! The date field of a event cannot be empty.");
        }
        this.taskType = "E";
    }

    /**
     * Constructor for Event which allows the specification of the isDone boolean.
     * This is used mainly when we parse the Event from storage, as it may be marked as done, but not
     * removed from the TaskList yet. It calls the two-argument constructor, then assigns isDone.
     *
     * @param description The description of the Event
     * @param isDone      Whether the Event is marked as done
     * @param eventDate   The date of the Event stored as a string
     * @throws DukeException Error if the format of the event is incorrect
     */
    public Event(String description, boolean isDone, String eventDate) throws DukeException {
        this(description, eventDate);
        this.isDone = isDone;
    }

    /**
     * Getter for the eventDate.
     *
     * @return the eventDate stored as a string
     */
    public String getEventDate() {
        return this.eventDate;
    }

    /**
     * Marks an Event task as done.
     * Throws a DukeException if the Event is already marked as done.
     * We return the task so that we can get its updated status more easily.
     *
     * @return the Event that has been marked as done
     * @throws DukeException if there is an error marking the Event as done
     */
    public Task markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException(":( OOPS!!! The event is already marked as done.");
        }
        Event completed = new Event(this.description, this.eventDate);
        completed.isDone = true;
        return completed;
    }

    /**
     * Gets the Event status as a string.
     * The string returned is formatted as specified on the CS2103T website.
     *
     * @return the task status as a string.
     */
    public String getTaskStatus() {
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription()
                + "(at: " + this.getEventDate() + ")");
    }

    /**
     * Gets the Event status a string, formatted for storage in the .txt file.
     * The string returned is formatted for storage as specified on the CS2103T website.
     *
     * @return the task status as a string for storage.
     */
    public String getStoredTaskStatus() {
        return (this.getTaskType() + " | " + this.getStatusIcon() + " | " + this.getTaskDescription()
                + " | " + this.getEventDate());
    }

}
