package duke.exception;

public class CannotSaveTasksException extends DukeException {

    @Override
    public String getResponseMessage() {
        return "Sorry, I am not able to save your tasks.";
    }

}