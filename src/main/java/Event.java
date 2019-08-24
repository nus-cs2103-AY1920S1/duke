import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of("type", "event", "description", this.description, "at", this.at, "is_done", this.isDone);
    }

    public static Event fromJson(JSONObject json) throws JSONException {
        // TODO: make sure the type is event
        Event rtn = new Event(json.getString("description"), json.getString("at"));
        rtn.isDone = json.getBoolean("is_done");
        return rtn;
    }
}
