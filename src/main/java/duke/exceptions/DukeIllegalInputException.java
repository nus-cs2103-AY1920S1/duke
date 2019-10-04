package duke.exceptions;

/**
 * This class is used to deal with the illegal input from users which is not the command.
 */
public class DukeIllegalInputException extends Exception {

    /**
     * The constructor of the class, throw the exception of illegal command.
     */
    public DukeIllegalInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
