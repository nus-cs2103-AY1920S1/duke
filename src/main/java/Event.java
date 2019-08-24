import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    private LocalDateTime at;
    public Event(String description, String at){
        super(description);

        this.at = LocalDateTime.parse(at,
                    DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][dd/M/yyyy HHmm][d/M/yyyy HHmm][dd/MM/yyyy HHmm]"));
    }

    public String getDate(){
        return at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.US));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HHmm", Locale.US)) + ")";
    }
}
