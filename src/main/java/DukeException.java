/**
 * Customised exceptions in the app.
 */
public class DukeException extends Exception{

    private String message;

    /**
     * Constructs a DukeException object.
     *
     * @param msg Message of the exception.
     */
    public DukeException(String msg) {
    super(msg);
    this.message = msg;
    }

    public String getMessege() {
        return this.message;
    }
}
