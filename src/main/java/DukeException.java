public class DukeException extends Exception {

    protected String message;

    public DukeException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
