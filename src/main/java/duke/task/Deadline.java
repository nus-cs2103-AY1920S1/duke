package duke.task;

import duke.Duke;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Map;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public Map<String, Object> toMap() {
        return Map.of("type", "deadline", "description", this.description, "by", Duke.dateTimeFormatter.format(this.by), "is_done", this.isDone);
    }

    /**
     * Parse to a Deadline object from JSONObject.
     *
     * @param json
     * @return parsed Deadline
     * @throws JSONException
     */
    public static Deadline fromJson(JSONObject json) throws JSONException {
        // TODO: make sure the type is deadline
        Deadline rtn = new Deadline(json.getString("description"), LocalDateTime.from(Duke.dateTimeFormatter.parse(json.getString("by"))));
        rtn.isDone = json.getBoolean("is_done");
        return rtn;
    }
}