public class Event extends Task{
    private String dateTime;

    Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    private String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("  [E][%s]%s(at:%s)", getStatusIcon(), getDescription(), getDateTime());
    }
}
