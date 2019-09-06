/**
 * Represents Exceptions from the Duke project.
 */
public class DukeException extends Exception {

    /**
     * Constructor to print custom error messages.
     *
     * @param error Error message
     */
    public DukeException(String error) {
        super(error);
    }
}
