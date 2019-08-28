/**
 * Represents an unknown command.
 */
public class UnknownTaskTypeException extends DukeException {
    /**
     * Creates a new exception that indicates that Duke does not understand the given command.
     */
    public UnknownTaskTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}