import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String datetime;

    Event(String description, String datetime) throws EmptyDescriptionException{
        super(description);

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("an event");
        }

        try {
            this.datetime = parseStringFormatDateTime(datetime);
        } catch (DateTimeParseException e) {
            this.datetime = datetime;
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
        return String.format("[E]%s (at: %s)", super.toString(), this.datetime);
    }
}
