package cs2103t.duke.exception;

public class NoIdGivenException extends DukeException {
    private String cmd;
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
