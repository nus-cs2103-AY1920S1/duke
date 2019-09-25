package weijie.duke.models;

import weijie.duke.utils.DateUtils;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructs a new deadline object with the given description and end date.
     * @param description Description of the deadline.
     * @param dateTime End date of the deadline.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        assert dateTime != null;
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
