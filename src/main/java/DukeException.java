import java.lang.Exception;

public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    public DukeException(String error) {
        super(error);
    }
}