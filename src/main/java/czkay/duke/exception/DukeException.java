package czkay.duke.exception;

/**
 * An Exception specific to the Duke program.
 */
public abstract class DukeException extends Exception {

    DukeException(String msg) {
        super(msg);
    }

}
