package cs2103t.duke.exception;

public class NoIDGivenException extends DukeException {
    private String cmd;
    public NoIDGivenException(String msg) {
        super(msg);
        this.cmd = msg;
    }

    @Override
    public String toString() {
        return String.format("Please give a ID with your %s command",
                this.cmd);
    }
}
