package cs2103t.duke.exception;

/**
 * Represents exceptions where no id is given with the done or delete command.
 */
public class NoIdGivenException extends DukeException {
    /** Command (done or delete). */
    private String cmd;

    /**
     * Constructs a NoIdGivenException.
     * @param msg command keyword that caused this exception.
     */
    public NoIdGivenException(String msg) {

        super(msg);
        this.cmd = msg;
    }

    @Override
    public String toString() {
        return String.format("Please give a ID with your %s command",
                this.cmd);
    }
}
