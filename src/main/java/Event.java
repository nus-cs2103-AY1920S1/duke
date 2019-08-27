import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    Event(String description, String at) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.at = LocalDateTime.parse(at, inputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMMM Y hh:mma");
        return "[E]" + super.toString() + " (at: " + at.format(outputFormat) + ")";
    }

}