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

/*    public Todo(String description, boolean completionStatus, LocalDateTime date) {
        super(description,completionStatus,date);
        taskType = new SimpleStringProperty("Todo");
    }*/

    @JsonCreator
    public Todo(@JsonProperty("description") String description,@JsonProperty("isDone") boolean completionStatus, @JsonProperty("dateDue") LocalDateTime date) {
        super(description,completionStatus,date);
        taskType = new SimpleStringProperty("Todo");
    }


    @Override
    public String getOverallStatus() {
        return "[T]" + getCurrentStatus() + description.getValue();
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone.getValue() ? 1 : 0;
        return "todo [" + myInt + "]" + description.getValue();
    }
}
