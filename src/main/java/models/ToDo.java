package models;

/**
 * Represents the attributes of a models.ToDo object, which is a sub-class of models.Task.
 */
public class ToDo extends Task {

    /**
     * Creates a models.ToDo object with a description.
     * The identity is also intialised as T, for all models.ToDo objects.
     *
     * @param description of the models.ToDo task.
     */
    public ToDo(String description) {

        super(description);
        this.identity = 'T';

    }

    /**
     * Recovery of a models.ToDo object based on the format of a task in the Duke.txt file that we save our information
     * into. Whether the task is done or not depends on the int value of the task when it is saved.
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
     * Returns the string value of a models.ToDo object.
     *
     * @return the identity of the models.ToDo task followed by the status icon and description.
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();

    }

    /**
     * Returns a string value to be saved in the Duke.txt file.
     *
     * @return a string in the Duke.txt file format for a models.ToDo object.
     */
    public String toTextFile() {

        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description;

    }

}
