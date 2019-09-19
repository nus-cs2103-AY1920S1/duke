package duke.task;

/**
 * Encapsulates a task object of type ToDo.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param title the topic of the ToDo.
     */
    public ToDo(String title) {
        super(title);
        this.type = "T";
    }

    /**
     * Returns the string representation of the to do object.
     * It takes the form of [type][done status][title].
     *
     * @return string representation of the to do object.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, status, title);
    }

    /**
     * Returns the data summary of this task to record this to do object in the database.
     *
     * @return the data summary of this to do object.
     */

    public String getSummaryForDatabase() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s", type, status, title);
    }
}
