public class DukeException extends Exception {
    protected String errormessage;

    public DukeException(String errormessage) {
        this.errormessage = errormessage;
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + this.errormessage
                + "\n____________________________________________________________";
    }
}
