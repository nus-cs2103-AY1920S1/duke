package seedu.duke.tasks;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private static final long serialVersionUID = -252890583907902347L;

    private final LocalDateTime time;

    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s%s", super.toString(),
                time != null
                        ? String.format(" (at: %s)", time.format(DATE_TIME_FORMATTER))
                        : ""
        );
    }
}
