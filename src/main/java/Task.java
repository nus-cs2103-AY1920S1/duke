import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract Map<String, Object> toMap();

    public static Task fromJson(JSONObject json) throws JSONException, DukeException {
        String type = json.getString("type");
        switch (type) {
            case "deadline":
                return Deadline.fromJson(json);
            case "event":
                return Event.fromJson(json);
            case "todo":
                return ToDo.fromJson(json);
            default:
                throw new DukeException("JSON parse error");
        }
    }
}