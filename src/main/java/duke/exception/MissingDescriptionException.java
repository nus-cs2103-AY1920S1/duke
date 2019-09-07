package duke.exception;

public class MissingDescriptionException extends DukeException {

    /**
     * Constructor for <code>MissingDescriptionException</code>.
     *
     * @param taskType Type of task.
     */
    public MissingDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}
