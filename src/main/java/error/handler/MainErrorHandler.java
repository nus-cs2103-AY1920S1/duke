package error.handler;

import error.TaskException;
import util.DukeMessage;
import util.DukeOutput;

public class MainErrorHandler {
    private static final DukeMessage GENERIC_ERROR_MESSAGE =
            new DukeMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    private TaskExceptionHandler taskExceptionHandler = new TaskExceptionHandler();

    public void handle(Exception e) {
        if (e instanceof TaskException) {
            taskExceptionHandler.handle((TaskException) e);
        } else {
            DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
        }
    }
}
