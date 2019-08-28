public class Event extends Task {
    protected String unformattedDateTime;
    protected String formattedDateTime;

    public Event(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        this.formattedDateTime = parser.convertStringToTime(dateTime, "event");
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, formattedDateTime);
    }

    public String toText() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, unformattedDateTime);
    }
}