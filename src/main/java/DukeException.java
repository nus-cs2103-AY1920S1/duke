/**
 * A custom exceptions class to represent exceptions specific to Duke.
 */

public class DukeException extends Exception {

    protected String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
