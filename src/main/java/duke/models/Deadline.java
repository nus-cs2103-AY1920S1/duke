package duke.models;

/**
 * Represents the attributes of a duke.models.Deadline object, which is a sub-class of duke.models.Task.
 */
public class Deadline extends Task {

    protected String date;

    /**
     * Creates a duke.models.Deadline object with a description.
     * The identity is also intialised as D, for all duke.models.Deadline objects.
     *
     * @param description of the duke.models.Deadline task.
     */
    public Deadline(String description, String date) {

        super(description);
        this.date = date;
        this.identity = 'D';

    }

    /**
     * Recovery of a duke.models.Deadline object based on the format of a task in the duke.Duke.txt file that we save
     * our information into. Whether the task is done or not depends on the int value of the task when it is saved.
     *
     * @param intDone if task is done, the int value == 1. If the task is not done, int value == 0.
     * @param description of the task.
     */
    public Deadline(int intDone, String description, String date) {

        super(intDone, description);
        this.identity = 'D';
        this.date = date;

        if (intDone == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }

    /**
     * Returns the string value of a duke.models.Deadline object.
     *
     * @return the identity of the duke.models.Deadline task followed by the status icon and description.
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a string value to be saved in the duke.Duke.txt file.
     *
     * @return a string in the duke.Duke.txt file format for a duke.models.Deadline object.
     */
    public String toTextFile() {

        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description + " | " + this.date;

    }

}
