public class Event extends Task {
	protected String time;

	public Event(String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (Exception e) {
			this.time = time;
		}
	}

	public Event(boolean isComplete, String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (Exception e) {
			this.time = time;
		}
		taskCompletionStatus = isComplete;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + time + ")";
	}
}
