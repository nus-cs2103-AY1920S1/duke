public class EventException extends Exception {
    private static String _noDesc = " OOPS!!! The description of an event cannot be empty.";
    private static String _noDate = " OOPS!!! The date of an event cannot be empty.";
    private static String _either = " OOPS!!! The description or date of an event cannot be empty.";
    private String _error;
    private int _type; // 1 = empty desc, 2 = no date, 3 = either

    public EventException(String msg, int type) {
        this._error = msg;
        this._type = type;
    }

    public String getMessage() {
        if (this._type == 1) {
            return _noDesc;
        } else if (this._type == 2){
            return _noDate;
        } else {
            return _either;
        }
    }
}
