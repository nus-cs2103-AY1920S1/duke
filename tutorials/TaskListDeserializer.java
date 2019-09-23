package Task;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.ObservableList;
import tasklist.Task;
import tasklist.TaskList;

import javax.swing.tree.TreeNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskListDesrerializer extends JsonDeserializer<TaskList> {

    @Override
    public TaskList deserialize(JsonParser jp , DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode tasks = node.get("tasks");
        Iterator<JsonNode> iter = tasks.elements();

        while (iter.hasNext()){
            String taskType = tasks.get("taskType").asText();
            String description = tasks.get("description").asText();
            Boolean isDone = tasks.get("isDone").asBoolean();
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            if (!tasks.get("dateDue").isNull()){
                LocalDateTime dateDue = ;
            }
            switch (taskType){
            case "Todo":


            }
        }
    }

}
