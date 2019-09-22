package error;

import java.lang.Exception;

/**
 * inherits from exception class.
 * handles all exception of duke
 * */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
