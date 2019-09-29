package duke.exception;

public class DukeException extends Exception {
    private ExceptionType exceptionType;

    public DukeException(ExceptionType exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    @Override
    public String getMessage() {
        return this.exceptionType.getMessage();
    }
}
