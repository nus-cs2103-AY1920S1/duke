public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + getMessage() + "\n\nFor list of commands, pelase send 'help'.";
    }
}
