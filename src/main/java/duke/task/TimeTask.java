package duke.task;

import java.time.LocalDateTime;

public class TimeTask extends Task {
    private LocalDateTime localDateTime;

    public TimeTask(String name, boolean isDone, String type, LocalDateTime localDateTime) {
        super(name, isDone, type);
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
