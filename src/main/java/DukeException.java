public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {
        super("OOPS! " + message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}