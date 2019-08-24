import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) throws EmptyDescriptionException {
        super(description);

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("a deadline");
        }

        try {
            this.deadline = parseStringFormatDateTime(deadline);
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
        }
    }

    private LocalDateTime stringToDatetime(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    private String datetimeToString(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h.mma"));
    }

    private String parseStringFormatDateTime(String stringFormatDateTime) {
        return this.datetimeToString(this.stringToDatetime(stringFormatDateTime));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
