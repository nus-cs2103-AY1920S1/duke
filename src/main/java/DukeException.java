/**
 * Describes the error that occurs from the user interaction.
 */

public class DukeException extends Exception {

    /**
     * An Exception that entails the reason of error.
     * @param message Error Message for the user to know what went wrong
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String errMessage = "\t " + this.getMessage();

        return errMessage;
    }
}
