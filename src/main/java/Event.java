import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Map;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of("type", "event", "description", this.description, "at", Duke.dateTimeFormatter.format(this.at), "is_done", this.isDone);
    }

    public static Event fromJson(JSONObject json) throws JSONException {
        // TODO: make sure the type is event
        Event rtn = new Event(json.getString("description"), LocalDateTime.from(Duke.dateTimeFormatter.parse(json.getString("at"))));
        rtn.isDone = json.getBoolean("is_done");
        return rtn;
    }
}
