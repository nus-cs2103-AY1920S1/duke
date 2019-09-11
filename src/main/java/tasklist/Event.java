package tasklist;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event type of task.
 */
public class Event extends Task {

    protected String at;

    public Event(String name, boolean completionStatus, LocalDateTime date) {
        super(name, completionStatus,date);
        taskType = new SimpleStringProperty("Event");
    }

    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + description.getValue() + "(at:"
                + dateDue.getValue().format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone.getValue() ? 1 : 0;
        return "event [" + myInt + "]" + description.getValue() + "/at"
                + dateDue.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}