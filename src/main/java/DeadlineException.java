public class DeadlineException extends TaskException {
    public DeadlineException(String message, boolean desc, boolean time) {
        super(message);
        this.desc = desc;
        this.time = time;
        this.type = "deadline";
    }
}
