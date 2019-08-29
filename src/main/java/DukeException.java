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

    /**
     * ToString method.
     *
     * @return String representation of the DukeException object.
     */
    @Override
    public String toString() {
        return message;
    }
}
