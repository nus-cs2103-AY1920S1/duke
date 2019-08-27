import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
	private String endDate;
	private LocalDateTime deadlineDate;
	private static DateTimeFormatter dateTimeFormatter
		= DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

	public DeadLine (String taskName, LocalDateTime deadlineDate) {
		super(taskName);
		this.deadlineDate = deadlineDate;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + deadlineDate.format(dateTimeFormatter) + ")";
	}

	public String getArchivalText() {
		return ("D" + " | " + super.getDoneStatusAsInt() + " | "  + super.getTaskName()
				+ " | " + deadlineDate.format(dateTimeFormatter));
	}
}
