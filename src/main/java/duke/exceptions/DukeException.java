package duke.exceptions;

/**
 * Represents exceptions that may arise when running the Duke programme.
 */

public class DukeException extends Exception {

    String exceptionString;

    /**
     * Constructs a DukeException object from an exception message string.
     * 
     * @param exceptionString The exception message
     */

    public DukeException(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    /**
     * Returns the DukeException message.
     * 
     * @return DukeException message
     */
    
    @Override
    public String toString() {
        assert exceptionString != null : "exceptionString should contain an actual String";
        return exceptionString;
    }

}
