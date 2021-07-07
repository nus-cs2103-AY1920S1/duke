package weijie.duke.models;

import weijie.duke.utils.DateUtils;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructs a new event object with the given description and end date.
     * @param description Description of the event.
     * @param dateTime End date of the event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        assert dateTime != null;
        this.dateTime = dateTime;
    }

    @Override
    public String getDateTime() {
        return "(at: " + dateTime.format(DateUtils.DUKE_DATETIME_OUTPUT_FORMAT) + ")";
    }

    @Override
    public String getTaskIcon() {
        return "[E]";
    }
}
