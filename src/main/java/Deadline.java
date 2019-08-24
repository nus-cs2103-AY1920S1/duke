import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of("type", "deadline", "description", this.description, "by", this.by, "is_done", this.isDone);
    }

    public static Deadline fromJson(JSONObject json) throws JSONException {
        // TODO: make sure the type is deadline
        Deadline rtn = new Deadline(json.getString("description"), json.getString("by"));
        rtn.isDone = json.getBoolean("is_done");
        return rtn;
    }
}