package cs2103t.duke.exception;

public class InvalidIdException extends DukeException {
    private String id;
    public InvalidIdException(String msg) {
        super(msg);
        this.id = msg;
    }

    @Override
    public String toString() {
        return String.format("\u2639 AIGOO!! %s is an invalid ID!", this.id);
    }
}
