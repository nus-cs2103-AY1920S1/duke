package weijie.duke.models;

public class Event extends Task {
    private final String dateTimeInfo;

    public Event(String description, String dateTimeInfo) {
        super(description);
        this.dateTimeInfo = dateTimeInfo;
    }

    @Override
    public String getDateTimeInfo() {
        return "(at: " + dateTimeInfo + ")";
    }

    @Override
    public String getTaskIcon() {
        return "[E]";
    }
}
