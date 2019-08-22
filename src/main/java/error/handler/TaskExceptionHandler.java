package error.handler;

import error.TaskException;
import util.DukeMessage;
import util.DukeOutput;

public class TaskExceptionHandler {
    public void handle(TaskException e) {
        DukeMessage errorMessage = new DukeMessage(e.getTaskErrorMessage());
        DukeOutput.printMessage(errorMessage);
    }
}
