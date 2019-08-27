public class IllegalCommandException extends Exception {
    protected String errormessage;

    public IllegalCommandException (String errormessage) {
        this.errormessage = errormessage;
    }

    public String toString() {
        return "____________________________________________________________\n"
                + " OOPS!!! " + this.errormessage
                + "\n____________________________________________________________";
    }
}
