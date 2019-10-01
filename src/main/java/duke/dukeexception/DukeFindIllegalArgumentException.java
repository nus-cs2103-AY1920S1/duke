package duke.dukeexception;

public class DukeFindIllegalArgumentException extends DukeException {
    public DukeFindIllegalArgumentException(String message) {
        super("You have entered " + message + " keywords in your search");
    }
}