package duke;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(CommandType.EVENT);
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
