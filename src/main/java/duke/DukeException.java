package duke;

public class DukeException extends Exception {
    public DukeException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "duke.DukeException: " + this.getMessage();
    }
}
