package exception;

/**
 * Responsible for problems occured during Done task handling.
 */
public class DoneException extends DukeException {

    public DoneException() {
        super("OOPS!!! The description of a done cannot be empty, please indicate which task is done :)");
    }

    public DoneException(String msg) {
        super(msg);
    }

}