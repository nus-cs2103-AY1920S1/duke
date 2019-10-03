/**
 * Works as the exception thrown in wrong inputs.
 */
public class DukeException extends Exception{
    /**
     * Generates an exception with custom message to be shown from the method it is being called.
     * @param message Stores message to be printed
     */
    public DukeException (String message) {
        super(message);
    }
}
