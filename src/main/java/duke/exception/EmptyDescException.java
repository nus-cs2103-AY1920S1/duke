package duke.exception;

/**
 *  Represents EmptyDescException that is thrown when the description
 *  of task or task number is empty.
 * The EmptyDescException class extends DukeException class.
 */
public class EmptyDescException extends DukeException {

    /** The type ot task EmptyDescException is thrown under **/
    private String type;

    /**
     *  Initialises a new EmptyDescException.
     *
     * @param type Type of task EmptyDescException is thrown under.
     */
    public EmptyDescException(String type) {
        this.type = type;
    }

    /**
     *  Returns the string representation of EmptyDescException.
     *
     *  @return String representation of exception.
      */
    public String getMessage() {
        return " OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
