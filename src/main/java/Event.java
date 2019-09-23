import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event which is one of 3 types of tasks.
 */
public class Event extends Task {
	private LocalDateTime eventDate;
	private static DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	/**
	 * Constructor of event object
	 * @param taskName the event name of the task
	 * @param eventDate the DateTime in which the event is going to happen
	 */
	public Event(String taskName, LocalDateTime eventDate) {
		super(taskName);
		this.eventDate = eventDate;
	}

	/**
	 * Converts the event object into a nice string representation
	 * @return a string representation of the event object
	 */
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + eventDate.format(dateTimeFormatter) + ")";
	}

	/**
	 * Converts the event object into a specific string presentation for archival purposes
	 * @return a string presentation of the event object in the archival format
	 */
	public String getArchivalText() {
		return ("E" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName()
				+ " | " + eventDate.format(dateTimeFormatter));
	}

	@Override
	public LocalDateTime getDateTime() {
		return this.eventDate;
	}
}
