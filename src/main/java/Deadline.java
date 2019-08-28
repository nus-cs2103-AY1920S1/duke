public class Deadline extends Task {
    protected String unformattedDateTime;
    protected String formattedDateTime;

    public Deadline(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        formattedDateTime = parser.convertStringToTime(dateTime, "deadline");
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, formattedDateTime);
    }

    public String toText() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, unformattedDateTime);
    }
}