package task;

<<<<<<< .merge_file_a03300
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

=======
/**
 * Defines the simple tasks and provides it's completion status.
 */
public class Todo extends Task {
    /**
         * Constructs the object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     */
>>>>>>> .merge_file_a03180
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

<<<<<<< .merge_file_a03300
=======
    /**
     * Constructs the object with default completion status of FALSE.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
>>>>>>> .merge_file_a03180
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

<<<<<<< .merge_file_a03300
=======
    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
>>>>>>> .merge_file_a03180
    public String toFile() {
        return "T | " + super.getStatusIcon() + " | " + super.getDescription();
    }
}