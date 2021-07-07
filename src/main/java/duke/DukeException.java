package duke;

/**
 * DukeException class that is thrown when any kind of exception is encountered during file loading, parsing of user
 * input, or execution of the command.
 */
public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
 }
