import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;
    protected DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = LocalDateTime.parse(by, format1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(format1) + ")";
    }
}
