package exception;

/**
 * A custom Exception class which is meant only for exceptions unique to Duke.
 */
public abstract class DukeException extends Exception {
    public DukeException() {

    }

    @Override
    public abstract String toString();
}
