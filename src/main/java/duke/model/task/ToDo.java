package duke.model.task;

/**
 * A class representing a to-do, inherits from task.
 */
public class ToDo extends Task {
    /**
     * Constructor for to-do, to be called for instantiating this object.
     *
     * @param description The description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Another constructor for to-do, to be called when storage loads from data stored locally.
     *
     * @param description The description of the to-do.
     * @param isDone Define whether a to-do is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string which is formatted to be stored in local storage.
     *
     * @return Returns a string which is formatted to be stored in local storage.
     */
    @Override
    public String getFileStringFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
