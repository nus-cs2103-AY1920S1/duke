package tasklist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event type of task.
 */
public class Event extends Task {

    @JsonCreator
    public Event(@JsonProperty("index") int index,
                 @JsonProperty("description") String description,
                 @JsonProperty("isDone") boolean completionStatus,
                 @JsonProperty("dateDue") LocalDateTime date) {
        super(index, description, completionStatus,date);
        taskType = new SimpleStringProperty("Event");
    }

    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + description.getValue() + "(at:"
                + dateDue.getValue().format(OUTPUT_FORMAT) + ")";
    }

}