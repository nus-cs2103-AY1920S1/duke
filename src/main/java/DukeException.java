public class DukeException extends Exception {
    public DukeException(String errorMessage, Throwable rootError) {
        super(errorMessage, rootError);
    }

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}