package dose.util.exception;

public class DoseException extends Exception {
    private ExceptionType exceptionType;

    public DoseException(ExceptionType exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    @Override
    public String getMessage() {
        return this.exceptionType.getMessage();
    }
}
