package duke.exception;

/**
 * A DukeException subclass that handles errors when taking in input.
 */
public class UnknownCmdException extends DukeException {
    /**
     * Constructs the exception when the command cannot be interpreted.
     */
    public UnknownCmdException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
