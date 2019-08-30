package task;

<<<<<<< .merge_file_a09892
=======
/**
 * Defines the general structure of tasks.
 */
>>>>>>> .merge_file_a07488
public class Task {
    private String description;
    private boolean isDone;

<<<<<<< .merge_file_a09892
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

=======
    /**
     * Constructs the Deadline object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs the object with default completion status of FALSE.
     * @param description Task description.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts completion status of task into symbols.
     * @return Notation of status completion.
     */
>>>>>>> .merge_file_a07488
    String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

<<<<<<< .merge_file_a09892
=======
    /**
     * Sets the completion status of task to TRUE.
     */
>>>>>>> .merge_file_a07488
    public void markDone() {
        isDone = true;
    }

<<<<<<< .merge_file_a09892
=======
    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
>>>>>>> .merge_file_a07488
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

<<<<<<< .merge_file_a09892
=======
    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
>>>>>>> .merge_file_a07488
    public String toFile() {
        return "";
    }

<<<<<<< .merge_file_a09892
    public String getDescription() {
=======

    /**
     * @return Task's description.
     */
    String getDescription() {
>>>>>>> .merge_file_a07488
        return description;
    }
}