import json.JsonWriter;
import java.util.Map;

public class DeadlineTask extends Task {
	private final String deadline;

	public DeadlineTask(String task, String deadline) {
		super(task);
		this.deadline = deadline;
	}

	DeadlineTask(Map<String, Object> dict) {
		super(dict);
		this.deadline = (String) dict.get("deadline");
	}

	@Override
	protected TaskType getTaskType() {
		return TaskType.Deadline;
	}

	@Override
	public String toString() {
		String baseDescription = super.toString();
		return String.format("%s (by: %s)", baseDescription, deadline);
	}

	@Override
	protected void toJson(JsonWriter.ObjectContext ctx) {
		super.toJson(ctx);
		ctx.writeField("deadline", this.deadline);
	}
}
