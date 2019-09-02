import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {
	private final LocalDateTime deadline;

	public DeadlineTask(String task, String deadline) {
		super(task);
		this.deadline = DateParser.parse(deadline);
	}

	@Override
	protected String getTypeMarker() {
		return "[D]";
	}

	@Override
	protected String getTaskType() {
		return "deadline";
	}

	@Override
	public String toString() {
		String baseDescription = super.toString();
		return String.format("%s (by: %s)", baseDescription,
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(deadline));
	}
}
