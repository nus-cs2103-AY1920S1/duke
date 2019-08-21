public class DeadlineException extends Exception {

    public DeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }

    public DeadlineException(String msg) {
        super(msg);
    }

}