package bot.duke.exception;

public class DukeTodoException extends DukeException {
    /**
     * Constructs the DukeTodoException object.
     */
    public DukeTodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }

}
