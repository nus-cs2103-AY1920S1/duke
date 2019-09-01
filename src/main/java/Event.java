import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDateTime time;

    Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            time = LocalDateTime.parse(at, formatter);
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    @Override
    public String saveData() {
        return "E | " + super.saveData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}