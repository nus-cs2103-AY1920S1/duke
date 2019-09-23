package tasklist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

/**
 * Represents the Todo type of task.
 */

public class Todo extends Task {
    @JsonCreator
    public Todo(@JsonProperty("index") int index,
                @JsonProperty("description") String description,
                @JsonProperty("isDone") boolean completionStatus,
                @JsonProperty("dateDue") LocalDateTime date) {
        super(index,description,completionStatus,date);
        taskType = new SimpleStringProperty("Todo");
    }


    @Override
    public String getOverallStatus() {
        return "[T]" + getCurrentStatus() + description.getValue();
    }
}
