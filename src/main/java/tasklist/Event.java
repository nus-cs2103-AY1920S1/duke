package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String name, boolean completionStatus, LocalDateTime date) {
        super(name, completionStatus,date);
    }

    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + description + "(at:" + date.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone ? 1 : 0;
        return "event [" + myInt + "]" + description + "/at"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}