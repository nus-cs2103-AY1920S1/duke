package duke.exception;

public class InvalidDateException extends DukeException {

    @Override
    public String getResponseMessage() {
        return "Sorry, I do not understand the date format.";
    }

}
