package duke.exception;

/**
 * Represents the base class of an Exception in Duke.
 */
public class DukeException extends Exception {

    /**
     * Specifies the message to be printed.
     * @return String which is the message of the exception.
     */
    @Override
    public String toString() {
        return " ☹ OOPS!!! ";
    }
}
