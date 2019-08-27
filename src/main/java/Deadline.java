import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.by = LocalDateTime.parse(by, inputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMMM Y hh:mma");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}