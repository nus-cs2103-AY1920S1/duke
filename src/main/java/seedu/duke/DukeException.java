package seedu.duke;

public class DukeException extends Exception {
    protected static String response;

    public DukeException(String message, String respond) {
        super(message);
        this.response = respond;
    }
}
