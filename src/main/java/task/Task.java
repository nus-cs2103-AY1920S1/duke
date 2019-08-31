package task;

public abstract class Task {
    public static int NOT_DONE = 0;
    public static int DONE = 1;
    private static final String TICK_SYMBOL = "\u2713"; // "✓"
    private static final String CROSS_SYMBOL = "\u2718"; // "✘"

    private String name;
    private int status;

    /**
     * Represents a single Task object in TaskList. Newly created Tasks are
     * set to NOT_DONE by default.
     *
     * @param name The name of the Task.
     */
    Task(String name) {
        this.name = name;
        this.status = NOT_DONE;
    }

    /**
     * Returns a single character representing the icon for the task type.
     *
     * @return The task icon for the type of task.
     */
    protected abstract String getTypeSymbol();

    /**
     * Returns additional information that is passed into the task object.
     *
     * @return The additional information of the task.
     * @see Deadline
     * @see Event
     */
    public abstract String getAdditionalInfo();

    /**
     * Returns the additional info in a manner used for printing directly
     * to the UI.
     *
     * @return The additional information of the task in formatted style.
     */
    protected abstract String displayAdditionalInfo();

    protected void setDone() {
        this.status = DONE;
    }

    protected void setNotDone() {
        this.status = NOT_DONE;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    private String getStatusSymbol() {
        if (status == DONE) {
            return TICK_SYMBOL;
        } else {
            return CROSS_SYMBOL;
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s %s %s", getTypeSymbol(),
                getStatusSymbol(),
                name,
                displayAdditionalInfo());
    }
}
