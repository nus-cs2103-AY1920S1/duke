public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String taskType, Throwable rootError) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.", rootError);
    }
}