package duke.models;

/**
 * Represents the attributes of a duke.models.ToDo object, which is a sub-class of duke.models.Task.
 */
public class ToDo extends Task {

    /**
     * Creates a duke.models.ToDo object with a description.
     * The identity is also intialised as T, for all duke.models.ToDo objects.
     *
     * @param description of the duke.models.ToDo task.
     */
    public ToDo(String description) {

        super(description);
        this.identity = 'T';

    }

    /**
     * Recovery of a duke.models.ToDo object based on the format of a task in the duke.Duke.txt file that we save
     * our information into. Whether the task is done or not depends on the int value of the task when it is saved.
     *
     * @param intDone if task is done, the int value == 1. If the task is not done, int value == 0.
     * @param description of the task.
     */
    public ToDo(int intDone, String description) {

        super(intDone, description);
        this.identity = 'T';

        if (intDone == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }

    /**
     * Returns the string value of a duke.models.ToDo object.
     *
     * @return the identity of the duke.models.ToDo task followed by the status icon and description.
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();

    }

    /**
     * Returns a string value to be saved in the duke.Duke.txt file.
     *
     * @return a string in the duke.Duke.txt file format for a duke.models.ToDo object.
     */
    public String toTextFile() {

        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description;

    }

}
