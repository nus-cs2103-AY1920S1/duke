package task;

/**
 * Specific extension of Task Class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Second constructor which used to read from hard disk storage.
     * @param isDone status of this task.
     * @param description description of this task.
     */
    public ToDo(String isDone, String description) {
        super(description);
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    /**
     * Used to store into hard disk storage for easy reading.
     * @return format output for hard disk storage.
     */
    public String toDataBase() {
        return "[T] | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}