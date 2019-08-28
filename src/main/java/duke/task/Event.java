package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, h:mma");
        return String.format(
                "[E][%s] %s (at: %s)",
                isDone ? "+" : " ",
                description,
                time.format(dateTimeFormatter)
        );
    }
}
