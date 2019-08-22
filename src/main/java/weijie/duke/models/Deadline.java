package weijie.duke.models;

public class Deadline extends Task {
    private final String dateTimeInfo;

    public Deadline(String description, String dateTimeInfo) {
        super(description);
        this.dateTimeInfo = dateTimeInfo;
    }

    @Override
    public String getDateTimeInfo() {
        return "(by: " + dateTimeInfo + ")";
    }

    @Override
    public String getTaskIcon() {
        return "[D]";
    }
}
