import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String by;
    private LocalDateTime time;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            time = LocalDateTime.parse(by, formatter);
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    @Override
    public String saveData() {
        return "D | " + super.saveData() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
