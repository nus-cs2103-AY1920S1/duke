package duke.exceptions;

/**
 * The class is to deal with the situation that the command is unable to be processed.
 */
public class DukeException extends Exception {

    /**
     * The constructor of the class, throw the exception of error in processing the command.
     */
    public DukeException() {
        super("Duke is unable to process the command!");
    }
}
