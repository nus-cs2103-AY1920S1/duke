public class EventException extends TaskException {
    public EventException(String message, boolean desc, boolean time) {
        super(message);
        this.desc = desc;
        this.time = time;
        this.type = "event";
    }
}
