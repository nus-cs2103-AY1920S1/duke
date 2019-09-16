package duke.task;

/**
 * The ToDo class handles all Todo Tasks,
 * which usually includes a description of the ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object which is also a Task.
     * @param desc Description of the ToDo Task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a string representation of a ToDo object.
     * @return String Returns a string representation of a ToDo object
     */
    public String toString() {
        return String.format("[T][%s] %s",
                this.getStatusIcon(), this._description);
    }

    /**
     * Returns a string representation of the ToDo object to be saved
     * into the hard disk file for the Duke program.
     * @return String Returns a the data representation of the ToDo Task
     */
    public String toData() {
        return String.format("T | %s | %s",
                this.getStatusIcon(), this._description);
    }
}
