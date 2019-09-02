import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventTask extends Task {
	private final LocalDateTime timing;

	public EventTask(String task, String timing) {
		super(task);
		this.timing = DateParser.parse(timing);
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
		return String.format("%s (at: %s)", baseDescription,
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(timing));
	}
}
