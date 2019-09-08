package duke.exception;

public class DukeException extends Exception {
    private String exceptionMessage;

    public DukeException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return this.exceptionMessage;
    }
}
