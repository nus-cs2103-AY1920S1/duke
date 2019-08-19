public class Event extends Task{
    private String dateTime;

    static Event of(String description, String dateTime) throws DukeException {
        if (description.length() == 0 || dateTime.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description or date/time of an event cannot be empty.");
        } else {
            return new Event(description, dateTime);
        }
    }

    private Event(String description, String dateTime) {
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
