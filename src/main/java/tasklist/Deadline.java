package tasklist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline type of task.
 */
public class Deadline extends Task {

    @JsonCreator
    public Deadline(@JsonProperty("description") String description,
                    @JsonProperty("isDOne") boolean completionStatus,
                    @JsonProperty("dateDue") LocalDateTime date) {
        super(description,completionStatus, date);
        taskType = new SimpleStringProperty("Deadline");
    }

    @Override
    public String getOverallStatus() {
        return "[D]" + getCurrentStatus() + description.getValue() + "(by:"
                + dateDue.getValue().format(OUTPUT_FORMAT) + ")";
    }

}
