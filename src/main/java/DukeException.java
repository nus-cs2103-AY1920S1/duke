public class DukeException extends Exception {
    protected String response;

    public DukeException(String message, String respond) {
        super(message);
        this.response = respond;
    }
}
