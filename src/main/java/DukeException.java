public class DukeException extends Exception {
    // handles any command that is not understandable by Duke
    private static String _message = " OOPS!!! I'm sorry, but I don't know what that means :-(";
    private String _error;

    public DukeException(String msg) {
        this._error = msg;
    }

    public String getMessage() {
        return this._message;
    }
}
