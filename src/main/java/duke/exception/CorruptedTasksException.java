package duke.exception;

public class CorruptedTasksException extends DukeException {

    @Override
    public String getResponseMessage() {
        return "Sorry, I am not able to load the tasks from saved file.";
    }

}