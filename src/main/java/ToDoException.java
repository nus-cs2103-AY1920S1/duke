public class ToDoException extends Exception{
    private static String _noDesc = " OOPS!!! The description of a todo cannot be empty.";
    private String _error;

    public ToDoException(String msg) {
        this._error = msg;
    }

    public String getMessage() {
        return _noDesc;
    }
}
