package weomucat.duke;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static weomucat.duke.Duke.DATETIME_FORMAT_PATTERN;
import static weomucat.duke.Duke.DATETIME_PARSE_PATTERN;

public class DeadlineTask extends Task {
	private ZonedDateTime by;

	public DeadlineTask(String description, String by) throws DateTimeParseException {
		super(description);

		// Parse 'by' into a ZonedDateTime object.
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_PATTERN)
				.withZone(ZoneId.systemDefault());
		this.by = ZonedDateTime.parse(by, dateTimeFormatter);
	}

	@Override
	public String toString() {
		// Format ZonedDateTime object into a String.
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
		return String.format("[D]%s (by: %s)", super.toString(), this.by.format(dateTimeFormatter));
	}
}
