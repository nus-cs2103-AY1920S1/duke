package duke.task;

/**
 * Tasks are the main data of the Duke application, describing something that
 * needs to be done.
 */
public class Task {
    /**
     * Checks whether the given String is the status icon for a done Task.
     *
     * @param icon A string containing a valid done or undone icon.
     * @return true if the icon represents a "done" status, and false otherwise.
     */
    public static boolean checkStatus(String icon) {
        assert icon.equals("+") || icon.equals(" "); // assume no change to icons
        String doneIcon = new Task().getStatusIcon();
        return icon.equals(doneIcon);
    }

    /** Description of task. */
    private String description;

    /** Whether the task has been completed. */
    private boolean isDone;

    /** Priority level of task. */
    private Priority priority;

    /**
     * Creates a new done Task with an empty description, for the convenient
     * checking of status icons.
     */
    private Task() {
        this("", true, Priority.NONE);
    }

    /**
     * Creates a new undone Task with the given description. By default, Tasks
     * are created with Priority level NONE.
     *
     * @param description Description of the Task. Description length should
     *                    be at most 50 characters (for now).
     */
    public Task(String description) {
        this(description, false, Priority.NONE);
    }

    /**
     * Creates a new Task with the given description, isDone status and level of
     * Priority.
     *
     * @param description Description of the Task. Description length should
     *                    be at most 50 characters (for now).
     * @param isDone Whether the Task has been completed or not.
     * @param priority Level of priority for the Task.
     */
    public Task(String description, boolean isDone, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Returns a plus symbol or space according to the isDone status of the
     * current task.
     *
     * @return The status icon associated with the current task.
     */
    private String getStatusIcon() {
        return isDone ? "+" : "-";
        // return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Returns a String of length 1 that indicates the current Task type.
     *
     * @return String indicating Task type.
     */
    public String getType() {
        return "-";
    }

    /**
     * Checks whether the current Task has a priority level above "None".
     *
     * @return True if this Task has a priority level above "None", and false
     *         otherwise.
     */
    private boolean hasPriority() {
        return this.priority != Priority.NONE;
    }

    /**
     * Gets the current Task priority level.
     *
     * @return Priority level of the current Task.
     */
    private Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the current Task priority to the given priority level.
     *
     * @param priority Priority level of the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Indicates that the current Task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicates that the current Task has not been completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a representation of the current Task in an appropriate
     * format for data storage.
     *
     * @return String representing the current Task.
     */
    public String formatAsData() {
        String delimiter = " | ";
        return getType() + delimiter + getStatusIcon() + delimiter
                + priority.asInteger() + delimiter + description;
    }

    /**
     * Returns the description of the Task along with an indication of its
     * isDone status and priority (if any).
     *
     * @return String containing the status and description of the current
     *         Task.
     */
    @Override
    public String toString() {
        String priorityString = hasPriority()
                ? "[" + getPriority().toString() + "] "
                : "";
        return "[" + getStatusIcon() + "] " + priorityString + description;
    }
}
