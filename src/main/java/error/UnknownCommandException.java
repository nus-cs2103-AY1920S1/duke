package error;

public class UnknownCommandException extends Exception {
    private String details;

    public UnknownCommandException() {
        details = null;
    }
    public UnknownCommandException(String details) {
        this.details = details;
    }

    @Override
    public String getMessage() {
        if (details == null) {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else {
            return "☹ OOPS!!! I'm sorry! " + details + " :-(";
        }
    }
}
