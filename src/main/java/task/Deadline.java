package task;

import misc.Parser;

public class Deadline extends Task {
    public String unformattedDateTime;
    private String formattedDateTime;

    Deadline(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        formattedDateTime = parser.convertStringToTime(dateTime, "deadline");
    }

    public Deadline(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        formattedDateTime = parser.convertStringToTime(dateTime, "deadline");
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, formattedDateTime);
    }
}
