package duke.exception;

public class DukeIllegalArgumentException extends Exception {

    private String message;

    public DukeIllegalArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
