package duke.ui;

import duke.error.DukeException;

import java.util.Optional;

/**
 * A Response contains Dukes's reply to a given Command, as well as some information about the current State of Duke,
 * after executing the last Command given to it.
 */
public class Response {
    private Optional<DukeException> dukeException;
    private String message;
    private boolean isActive;

    private Response(String message, DukeException dukeException, boolean isActive) {
        this.message = message;
        this.dukeException = Optional.ofNullable(dukeException);
        this.isActive = isActive;
    }

    /**
     * Returns a Response containing the message from Duke after successfully executing a Command, signal that
     * the Response was not caused by an error, and the activity status of Duke at the time of the Response.
     *
     * @param message The message from Duke in reply to the given Command
     * @param isActive True if Duke is able to respond to further Commands, false otherwise.
     * @return The Response from successfully executing a Command
     */
    public static Response fromString(String message, boolean isActive) {
        return new Response(message, null, isActive);
    }


    /**
     * Returns a Response containing the error message from Duke after an error occurs after user input, signal that
     * the Response was caused by an error, and the activity status of Duke at the time of the Response.
     *
     * @param e The DukeException thrown when the error occurs
     * @param isActive True if Duke is able to respond to further Commands, false otherwise.
     * @return The Response due to the error that occurred in Duke.
     */
    public static Response fromError(DukeException e, boolean isActive) {
        return new Response(e.getMessage(), e, isActive);
    }

    /**
     * Returns true if the Response was a result of a DukeException, false otherwise.
     *
     * @return True if the Response was a result of a DukeException, False otherwise.
     */
    public boolean wasCausedByError() {
        return dukeException.isPresent();
    }

    /**
     * Returns true is Duke can take further user input, false otherwise
     *
     * @return True is Duke can take further user input, False otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the String representation of the Response
     *
     * @return the String representation of the Response
     */
    @Override
    public String toString() {
        return message;
    }
}