package exception;

import java.util.NoSuchElementException;

/**
 * Represents an exception that indicates to user that no input is detected.
 */
public class VoidDukeCommand extends NoSuchElementException {
    private static final long serialVersionUID = 1L;

    public VoidDukeCommand() {
        super("I can't do anything if you don't tell me what to do...");
    }
}
