import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents one of 3 types of tasks. Has a LocalDateTime attribute to represent the dateTime at which the task should
 * 			be completed.
 */
public class DeadLine extends Task {
	private String endDate;
	private LocalDateTime deadlineDate;
	private static DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	/**
	 * Constructor for the deadline object
	 * @param taskName name of the deadline task
	 * @param deadlineDate LocalDateTime of when the deadline is due by
	 */
	public DeadLine (String taskName, LocalDateTime deadlineDate) {
		super(taskName);
		this.deadlineDate = deadlineDate;
	}

	public LocalDateTime getDateTime() {
		return this.deadlineDate;
	}

	/**
	 * Formats the deadline object in a more readable form for informing the user about the task
	 * @return a string representation of the deadline object
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + deadlineDate.format(dateTimeFormatter) + ")";
	}

	/**
	 * Formats the deadline object to be archived. Format is crucial for reading the task from the archival file later
	 * 		on.
	 * @return a string representation of the deadline object for archival purposes.
	 */
	public String getArchivalText() {
		return ("D" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName()
				+ " | " + deadlineDate.format(dateTimeFormatter));
	}
}
