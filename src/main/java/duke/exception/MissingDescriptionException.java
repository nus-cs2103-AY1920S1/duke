package duke.exception;

public class MissingDescriptionException extends DukeException {

    /**
     * Initialises a EmptyDescriptionException.
     *
     * @param taskType The task type entered.
     */
    public MissingDescriptionException(String taskType) {
        super("The description of a " + taskType
                + " cannot be empty. Please try again with the following format...\n"
                + getCorrectFormat(taskType));
    }
}
