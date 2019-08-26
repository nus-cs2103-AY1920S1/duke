package duke;

/**
 * Generic abstraction for exceptions related to duke operations.
 */
public class DukeExceptions extends Exception {
    /** Message to display to the user interface */
    private String displayMsg;

    /**
     * Private constructor that calls Exception's constructor.
     * Passes the provided message as the error message
     * to Exception's constructor.
     *
     * @param message The error message string.
     */
    private DukeExceptions(String message) {
        super(message);
        this.displayMsg = message;
    }

    /**
     * Default constructor that sets the error and display message.
     *
     * @param message The informative error message string.
     * @param displayMsg The display message string.
     */
    protected DukeExceptions(String message, String displayMsg) {
        this(message);
        this.displayMsg = displayMsg;
    }

    /**
     * Gets the display message.
     *
     * @return The string display message.
     */
    public String getDisplayMsg() {
        return this.displayMsg;
    }
}
