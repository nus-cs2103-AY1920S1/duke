package main.java.exception;

/**
 * DukeException class handles all the exception that can be thrown by the program.
 */
public class DukeException extends Exception {
    
    /**
     * Class constructor
     *
     */
    public DukeException() {
        super();
    }
    
    /**
     * Class constructor
     *
     * @param message message to be tagged to the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
