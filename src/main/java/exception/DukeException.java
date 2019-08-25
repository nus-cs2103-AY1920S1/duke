package exception;

public class DukeException extends Exception {
    String msg;

    public DukeException(String msg) {
        super(msg);
    }

    public String toString() {
        return msg;
    }
}
