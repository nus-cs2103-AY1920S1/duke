package exception;

import misc.Ui;

/**
 * Represents an exception that indicates to user that the input command does not exist.
 */
public class InvalidDukeCommand extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public InvalidDukeCommand() {
        super(Ui.spaces(5) + "I'm sorry, but I don't know what that means... \u2639");
    }
}
