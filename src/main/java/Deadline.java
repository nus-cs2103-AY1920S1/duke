public class Deadline extends Task {
	protected String time;

	public Deadline(String description, String time) {
		super(description);
		try {
			this.time = Parser.parseDateTime(time);
		} catch (Exception e) {
			this.time = time;
		}
	}

	public Deadline(boolean isComplete, String description, String time) {
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
		return "[D]" + super.toString() + " (by: " + time + ")";
	}
}
