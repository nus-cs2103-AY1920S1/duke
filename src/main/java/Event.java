public class Event extends Task {
	private String eventDate;

	public Event(String taskName, String eventDate) {
		super(taskName);
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + eventDate + ")";
	}
}
