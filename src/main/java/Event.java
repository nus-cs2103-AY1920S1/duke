import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
	private LocalDateTime eventDate;
	private static DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	public Event(String taskName, LocalDateTime eventDate) {
		super(taskName);
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + eventDate.format(dateTimeFormatter) + ")";
	}

	public String getArchivalText() {
		return ("E" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName()
				+ " | " + eventDate.format(dateTimeFormatter));
	}
}
