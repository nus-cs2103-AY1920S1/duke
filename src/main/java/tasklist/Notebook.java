package tasklist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Notebook extends Task {

    @JsonCreator
    public Notebook(@JsonProperty("description") String description,
                @JsonProperty("isDone") boolean completionStatus,
                @JsonProperty("dateDue") LocalDateTime date) {
        super(description,completionStatus,date);
        taskType = new SimpleStringProperty("notebook");
    }


    @Override
    public String getOverallStatus() {
        return "[N]" + description.getValue();
    }

}
