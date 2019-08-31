public class InvalidDateTimeException extends DukeException {
    String dateTime;

    public InvalidDateTimeException(String dateTime) {
        super();
        this.dateTime = dateTime;
    }

    public String getInvalidDateTime() {
        return dateTime;
    }
}
