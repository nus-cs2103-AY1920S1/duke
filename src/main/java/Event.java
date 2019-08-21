public class Event extends Task {
	protected String at;

	public Event(String description, String at) {
		super(description);
		try {
			this.at = Parser.parseDateTime(at);
		} catch (Exception e) {
			this.at = at;
		}
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + at + ")";
	}
}
