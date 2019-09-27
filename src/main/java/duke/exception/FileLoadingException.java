package duke.exception;

public class FileLoadingException extends DukeException {

    protected String errorMessage;

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param errorMessage the detail message. It is saved for later retrieval by the Throwable.getMessage() method.
     */
    public FileLoadingException(String errorMessage) {
        super(errorMessage);
    }

}
