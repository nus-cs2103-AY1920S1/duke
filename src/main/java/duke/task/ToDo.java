package duke.task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of("type", "todo", "description", this.description, "is_done", this.isDone);
    }

    public static ToDo fromJson(JSONObject json) throws JSONException {
        // TODO: make sure the type is todo
        ToDo rtn = new ToDo(json.getString("description"));
        rtn.isDone = json.getBoolean("is_done");
        return rtn;
    }
}
