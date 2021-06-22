package exception;

/**
 * Responsible for problems occured during Event task handling.
 */
public class EventException extends DukeException {

    public EventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }

    public EventException(String msg) {
        super(msg);
    }

}