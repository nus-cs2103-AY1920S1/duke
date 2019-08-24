public class EventTask extends Task {
	private final String timing;

	public EventTask(String task, String timing) {
		super(task);
		this.timing = timing;
	}

	@Override
	protected String getTypeMarker() {
		return "[E]";
	}

	@Override
	protected String getTaskType() {
		return "event";
	}

	@Override
	public String toString() {
		String baseDescription = super.toString();
		return String.format("%s (at: %s)", baseDescription, timing);
	}
}
