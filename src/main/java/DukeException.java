public abstract class DukeException extends Exception {

    DukeException(String message) {
        super("\u2639 OOPS! " + message);
    }
}
