package task;

import misc.Parser;

public class Event extends Task {
    public String unformattedDateTime;
    private String formattedDateTime;

    Event(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        this.formattedDateTime = parser.convertStringToTime(dateTime, "event");
    }

    public Event(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        this.formattedDateTime = parser.convertStringToTime(dateTime, "event");
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, formattedDateTime);
    }
}
