/**
 * Error shown by Duke when description of a task is empty.
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Creates an error indicating that description of task is empty.
     * @param taskType the specific type of the task given.
     * @param rootError the error given by Java.
     */
    public EmptyDescriptionException(String taskType, Throwable rootError) {
        super("WOOf?? The description of a " + taskType + " cannot be empty.", rootError);
    }
}