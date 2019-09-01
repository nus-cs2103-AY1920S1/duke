/**
 * This exception is used when User enters an invalid command.
 */
public class IllegalCommandException extends Exception {
    protected String errormessage;

    /**
     * An IllegalCommandException is instantiated when an error message
     * parameter is being passed through.
     * @param errormessage a String to display the error.
     */
    public IllegalCommandException (String errormessage) {
        this.errormessage = errormessage;
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + " OOPS!!! " + this.errormessage
                + "\n____________________________________________________________";
    }
}
