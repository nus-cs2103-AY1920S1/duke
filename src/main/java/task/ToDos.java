package task;

/**
 * The ToDo class is used to represent and manage Tasks.ToDos within
 * Core.Duke.
 */

public class ToDos extends Task {

    /**
     * Creates a new ToDo with the specified detail.
     * @param task Detail of what the ToDo requires to be done.
     */
    public ToDos(String task) {
        super(task);
    }

    /**
     * Creates a new ToDo with the specified detail and 
     * its completition status being predetermined.
     * @param task Detail of what the ToDo requires to be done.
     * @param complete Boolean to determine whether the ToDo has been completed or not.
     */
    public ToDos(String task, boolean complete) {
        super(task, complete);
    }

    /**
     * Returns the String representation of a ToDo as it is
     * to be stored in the local file.
     */
    @Override
    public String toStringForFile() {
        String isComplete = this.complete ? "1" : "0";
        return "T | " + isComplete + " | " + task; 
    }

    /**
     * Returns the String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}