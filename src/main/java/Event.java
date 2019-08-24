public class Event extends TaskWithDateTime {

    Event(String description, String dateTime) throws EmptyDescriptionException {
        super("an event", description, dateTime, "0");
    }

    Event(String description, String dateTime, String isDone) throws EmptyDescriptionException {
        super("an event", description, dateTime, isDone);
    }

    @Override
    String getTypeCode() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.getDateTime());
    }
}
