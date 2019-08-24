public class DukeEmptyDateException extends DukeException {
    private String cmd;
    public DukeEmptyDateException (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return oops + "The date of a " + cmd + " cannot be empty.";
    }
}
