package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    Date by;
    SimpleDateFormat formatter;

    public Event(String description, Date by) {
        super(description);
        this.by = by;
        formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    @Override
    public String formatToWrite() {
        if (this.done) {
            return String.format("E | %d | %s | %s", 1, this.description, formatter.format(this.by));
        } else {
            return String.format("E | %d | %s | %s", 0, this.description, formatter.format(this.by));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatter.format(this.by));
    }
}
