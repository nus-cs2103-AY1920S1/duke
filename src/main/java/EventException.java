public class EventException extends Exception {

    public EventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }

    public EventException(String msg) {
        super(msg);
    }

}