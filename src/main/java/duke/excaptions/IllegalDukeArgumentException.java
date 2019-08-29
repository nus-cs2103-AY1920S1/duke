package duke.excaptions;

/**
 * the exception thrown when the todo command has no content
 */
public class IllegalDukeArgumentException extends Exception {
    public IllegalDukeArgumentException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
