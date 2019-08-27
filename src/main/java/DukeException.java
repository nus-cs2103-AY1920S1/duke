public class DukeException extends Exception{

    String errorDescription;

    DukeException(String errorDescription) {
        super(errorDescription);
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        return errorDescription;
    }
}
