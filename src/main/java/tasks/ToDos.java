package tasks;

/**
 * The ToDos class is a type of task. It contains description
 * of the task to be completed.
 */
public class ToDos extends Task {
    /**
     * Constructs and initializes the attributes of a new ToDos object.
     * @param description Description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructs and initializes the attributes of a new ToDos object.
     * @param description Description of the task.
     * @param checker Check the status of the task recorded.
     */
    public ToDos(String description, String checker) {
        super(description, checker);
    }

    /**
     * Prints the task in the desired format for storing in text file.
     * @return Returns formatted String representing ToDos.
     */
    public String getFormattedString() {
        return String.format("T | %s | %s", super.getStatusIcon(), description);
    }

    /**
     * Overrides the underlying Object toString method to print ToDos in
     * the desired format to show to users.
     * @return Returns formatted String representing ToDos.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

