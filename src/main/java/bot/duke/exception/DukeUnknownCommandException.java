package bot.duke.exception;

public class DukeUnknownCommandException extends DukeException {
    /**
     * Constructs the DukeUnknownCommandException.
     */
    public DukeUnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
