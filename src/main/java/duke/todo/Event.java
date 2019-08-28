package duke.todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    public Event(String description, String date) {
        super(description);

        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.date = LocalDateTime.parse(date, formatter);
    }

    @Override
    public String getFormattedTask() {
        return "E | " + super.getDescription() +
                " /at " + date;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + this.getDescription() +
                " (on: " + date.format(formatter) + ")";
    }
}
