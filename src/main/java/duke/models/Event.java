package duke.models;

/**
 * Represents the attributes of an duke.models.Event object, which is a sub-class of duke.models.Task.
 */
public class Event extends Task {

    protected String dateAndTime;

    /**
     * Creates an duke.models.Event object with a description.
     * The identity is also intialised as E, for all duke.models.Event objects.
     *
     * @param description of the duke.models.Event task.
     */
    public Event(String description, String at) {

        super(description);
        this.dateAndTime = at;
        this.identity = 'E';

    }

    /**
     * Recovery of an duke.models.Event object based on the format of a task in the duke.Duke.txt file that we save our
     * information into. Whether the task is done or not depends on the int value of the task when it is saved.
     *
     * @param intDone if task is done, the int value == 1. If the task is not done, int value == 0.
     * @param description of the task.
     */
    public Event(int intDone, String description, String dateAndTime) {

        super(intDone, description);
        this.identity = 'E';
        this.dateAndTime = dateAndTime;

        if (intDone == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }

    /**
     * Returns the string value of an duke.models.Event object.
     *
     * @return the identity of the duke.models.Event task followed by the status icon and description.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }

    /**
     * Returns a string value to be saved in the duke.Duke.txt file.
     *
     * @return a string in the duke.Duke.txt file format for an duke.models.Event object.
     */
    public String toTextFile() {

        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.dateAndTime;

    }

}
