package duke.core;

/*
 * Encapsulates a DukeException object in charge of all exceptions related to command line.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object.
     * @param errorMessage String of error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}