public class DukeEmptyDescException extends DukeException {
    private String cmd;
    public DukeEmptyDescException (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return oops + "The description of a " + cmd + " cannot be empty.";
    }
}
