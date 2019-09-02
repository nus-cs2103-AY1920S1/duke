package duke.task;

/**
 * An abstract class of a task to be inputted by the user.
 */
public abstract class Task {

    /** The description of the user task. */
    private String description;
    /** Shows whether this Task is completed by the user or not. */
    private boolean isDone;

    /**
     * Constructs a Task object with the given description.
     * isDone field is set to false by default.
     *
     * @param description The description of this Task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the given description and status.
     *
     * @param description The description of this Task.
     * @param isDone The status of this Task.
     */
    Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the type of this Task as a single capital letter.
     *
     * @return They type of this Task in a single capital letter.
     * @see TodoTask#getType()
     * @see DeadlineTask#getType()
     * @see EventTask#getType()
     */
    public abstract String getType();

    /**
     * Returns the status (as a tick or X mark) and the description of this Task.
     *
     * @return Status and the description of this Task.
     */
    public String getStatus() {
        String icon = (isDone ? "\u2713" : "\u2718"); //tick or X symbol
        return String.format("[%s] %s", icon, this.description);
    }

    /**
     * Set isDone field to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of this Task.
     *
     * @return The description field.
     */
    @Override
    public String toString() {
        return this.description;
    }

}