package duke.exception;

public class DukeIllegalIndexException extends Exception {

    private String message;

    public DukeIllegalIndexException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
