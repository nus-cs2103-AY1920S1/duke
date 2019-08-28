package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, h:mma");
        return String.format(
                "[D][%s] %s (by: %s)",
                isDone ? "+" : " ",
                description,
                deadline.format(dateTimeFormatter)
        );
    }
}
