package duke.task;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("\u2639 OOPS!!! " + message);
    }
}
