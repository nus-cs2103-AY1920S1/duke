package duke;

public class DukeException extends Exception {
    private static final String ERROR_INVALID_INPUT = "I'm sorry, but I don't know what that means :-(";

    public DukeException() {
        super(String.format("☹ OOPS!!! %s", ERROR_INVALID_INPUT));
    }

    public DukeException(String message) {
        super(String.format("☹ OOPS!!! %s", message));
    }
}
