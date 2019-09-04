/**
 * Encapsulate the exception thrown when errors occurs when the list file is not found.
 */
public class DukeFileNotFoundException extends DukeIoException {

    public DukeFileNotFoundException(String msg) {
        super(msg);
    }
}
