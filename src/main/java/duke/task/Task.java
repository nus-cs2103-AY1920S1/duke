package duke.task;

/**
 * Task class represents an act a user wants to perform.
 *
 * @author scwaterbear
 */
public class Task {

    boolean isDone;
    private String description;

    /**
     * Class constructor.
     *
     * @param description description of task.
     */
    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Class constructor that must set task status when instantiated.
     *
     * @param description description of task.
     * @param isDone      set status of task.
     */
    Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns whether self's description contains the keyword.
     *
     * @param keyword search term.
     * @return boolean whether description contains the search term.
     */
    boolean hasKeywordsInDescription(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a string representation of the task status and description.
     *
     * @return String task status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task status and description for data storage.
     *
     * @return String task status and description.
     */
    public String toDataFormat() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return " | " + isDoneInt + " | " + description;
    }
}