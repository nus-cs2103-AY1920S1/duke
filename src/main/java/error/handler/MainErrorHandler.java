package error.handler;

import error.ConfigurationException;
import error.TaskException;
import error.UnknownCommandException;
import util.DukeMessage;
import util.DukeOutput;

public class MainErrorHandler {

    private static final DukeMessage UNKNOWN_COMMAND_ERROR_MESSAGE =
            new DukeMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    private static final DukeMessage GENERIC_ERROR_MESSAGE =
            new DukeMessage("☹ OOPS!!! Something unexpected happened!!!");

    private TaskExceptionHandler taskExceptionHandler = new TaskExceptionHandler();

    public void handle(Exception e) {
        if (e instanceof TaskException) {
            taskExceptionHandler.handle((TaskException) e);
        } else if (e instanceof UnknownCommandException) {
            DukeOutput.printMessage(UNKNOWN_COMMAND_ERROR_MESSAGE);
        } else if (e instanceof ConfigurationException) {
            DukeMessage errorMessage = new DukeMessage("ERROR unable to configure app due to: ")
                    .newLine()
                    .append(e.getMessage());
            DukeOutput.printMessage(errorMessage);
        } else {
            DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
        }
    }
}
