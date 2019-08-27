import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime at;
    protected DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, String at){
        super(description);
        this.at = LocalDateTime.parse(at, format1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(format1) + ")";
    }
}
