package SerSlacksalot.exception;

/**
 * The DukeException returns a message detailing the exceptions thrown in the Duke program.
 */
public class DukeException extends Exception {

    protected String message;

    public DukeException(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return this.message;
    }
}
