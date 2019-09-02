import json.JsonWriter;
import java.util.Map;

public class EventTask extends Task {
	private final String timing;

	public EventTask(String task, String timing) {
		super(task);
		this.timing = timing;
	}

	EventTask(Map<String, Object> dict) {
		super(dict);
		this.timing = (String) dict.get("timing");
	}

	@Override
	protected TaskType getTaskType() {
		return TaskType.Event;
	}

	@Override
	public String toString() {
		String baseDescription = super.toString();
		return String.format("%s (at: %s)", baseDescription, timing);
	}

	@Override
	public void toJson(JsonWriter.ObjectContext ctx) {
		super.toJson(ctx);
		ctx.writeField("timing", this.timing);
	}
}
