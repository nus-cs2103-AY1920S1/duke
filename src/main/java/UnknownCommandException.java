public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
