package duke.models;

/**
 * Representing the attributes and behavior of a general duke.models.Task. The abstract methods will be more specific
 * for the child classes of the duke.models.Task class.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected char identity;
    protected boolean isPriority;

    /**
     * Creating a task object. It is the default for a task to be not done upon creation.
     *
     * @param description of the task being created.
     */
    public Task(String description) {

        this.description = description;
        this.isDone = false;
        this.isPriority = false;

    }

    /**
     * Recovery of a task object based on the format of a task in the duke.Duke.txt file that we save our information
     * into. Whether the task is done or not depends on the int value of the task when it is saved.
     *
     * @param intDone if task is done, the int value == 1. If the task is not done, int value == 0.
     * @param description of the task.
     */
    public Task(int intDone, String description) {

        this.description = description;

        if (intDone == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }

    /**
     * Returns the status icon of the task. This is dependent on whether the task is done or not.
     *
     * @return the icon, which is a string value.
     */
    public String getStatusIcon() {

        return isDone ? "[\u2713]" : "[\u2718]"; //return tick or X symbols

    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {

        this.isDone = !isDone;
    }

    /**
     * Marks the task as high priority.
     */
    public void markAsPriority() {

        this.isPriority = true;
        this.description += "     " + this.priorityDescription();

    }

    /**
     * Returns the string value of a duke.models.Task object.
     *
     * @return the status icon and description of the task.
     */
    @Override
    public String toString() {

        return getStatusIcon() + " " + this.description;

    }

    /**
     * Returns a string to be saved in the duke.Duke.txt file. It is an abstract method that needs to be implemented
     * by the sub classes.
     *
     * @return string value to be saved in the duke.Duke.txt file.
     */
    public abstract String toTextFile();

    /**
     * Returns the string value of the description attribute since it has only protected access.
     *
     * @return string value of description of Task object.
     */
    public String getDescription() {

        return this.description;

    }

    /**
     * Returns a boolean value on whether the description of task contains a particular string value.
     * This is for the Find Command.
     *
     * @param text particular string value we are checking if present.
     * @return true if Task object's description contains the keyword. False if not.
     */
    public boolean contains(String text) {

        return this.description.contains(text);

    }

    public String priorityDescription() {

        return "**** Priority ****";
    }


}
