package duke.tasks;

/** Implements the logic behind a ToDo Task */
public class ToDo extends Task {
    /**
     * Constructor
     *
     * @param description String description of the ToDo Task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of ToDo object
     *
     * @return String representation of ToDo object
     */
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[T][" + statusIcon + "] " + this.description;
    }

    /**
     * Generates a String representation of the ToDo Task in a format
     * that is compatible for the Storage object to read and write.
     *
     * @return String representation of the Task (compatibility with Storage class)
     */
    public String getStorageFormat() {
        String storageString = "T | " + super.getStorageFormat();
        return storageString;
    }
}
