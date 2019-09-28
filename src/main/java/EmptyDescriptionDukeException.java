/**
 * An Exception thrown when the user does not properly describe a task. The exception is defined by the task type
 * and the missing description.
 */
public class EmptyDescriptionDukeException extends DukeException {
    String id;
    String desc = " ";

    /**
     * Constructs an EmptyDescription Exception for Duke.
     *
     * @param id   the type of task.
     * @param desc the missing description of the task.
     */
    public EmptyDescriptionDukeException(String id, String desc) {
        super(id);
        this.id = id;
        this.desc = " " + desc + " ";
    }

    /**
     * Constructs an EmptyDescription Exception for Duke.
     *
     * @param id The Type of task.
     */
    public EmptyDescriptionDukeException(String id) {
        super(id);
        this.id = id;
    }

    /**
     * Gives out a string that describes the exception.
     *
     * @return A string that describes the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description" + desc + "of a " + id + " task cannot be empty.";
    }
}
