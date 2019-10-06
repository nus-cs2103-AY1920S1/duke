package duke;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dueDate;

    public DeadlineCommand(String description, LocalDateTime dueDate) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
