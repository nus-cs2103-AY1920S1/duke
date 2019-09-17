package duke.exception;

import java.io.IOException;

public class DukeIllegalActionException extends IllegalArgumentException {
    public DukeIllegalActionException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
