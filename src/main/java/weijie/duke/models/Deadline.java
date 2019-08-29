package weijie.duke.models;

import weijie.duke.utils.DateUtils;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getDateTime() {
        return "(by: " + dateTime.format(DateUtils.DUKE_DATETIME_OUTPUT_FORMAT) + ")";
    }

    @Override
    public String getTaskIcon() {
        return "[D]";
    }
}
