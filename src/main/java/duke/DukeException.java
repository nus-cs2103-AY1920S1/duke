package duke;

/**
 * Customized DukeException to represent the errors encountered in program.
 */
public class DukeException extends Exception {
    private String description;

    /**
     * Constructor of DukeException.
     *
     * @param description Description of the Error encountered.
     */
    public DukeException(String description) {
        this.description = description;
    }

    /**
     * Checks if a DukeException should be thrown or not based on evaluating
     * the given predicate.
     *
     * @param shouldThrowError Given evaluation predicate.
     * @param message Error Message for DukeException if thrown.
     * @throws DukeException If shouldThrowError evaluates to true.
     */
    public static void checkValidity(boolean shouldThrowError, String message) throws DukeException {
        if (shouldThrowError) {
            throw new DukeException(message);
        }
    }

    @Override
    public String toString() {
        return this.description;
    }
}
