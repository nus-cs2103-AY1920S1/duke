public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return Duke.horizontalLine + "\n"
                + "     \u2639 OOPS!!! "
                + getMessage() + "\n"
                + Duke.horizontalLine + "\n";
    }
}
