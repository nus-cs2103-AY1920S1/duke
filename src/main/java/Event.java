public class Event extends DatedTask {

    static Event of(String description, String dateTime) throws DukeException {
        if (description.length() == 0 || dateTime.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description or date/time of an event cannot be empty.");
        } else {
            return new Event(description, dateTime);
        }
    }

    private Event(String description, String dateTime) {
        super("E", description, dateTime);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" (at: %s)", getDateTime()));
    }
}
