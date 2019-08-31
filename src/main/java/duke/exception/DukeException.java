package duke.exception;

public class DukeException extends Exception{

    private String errorDescription;

    public DukeException(String errorDescription) {
        super(errorDescription);
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        return errorDescription;
    }
}
