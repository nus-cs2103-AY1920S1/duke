public class IllegalCommandException extends Exception {
    private String errorMessage;

    protected IllegalCommandException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return "____________________________________________________________\n"
                + " OOPS!!! " + this.errorMessage
                + "\n____________________________________________________________";
    }
}
