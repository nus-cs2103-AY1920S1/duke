import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;
    public Deadline(String description, String by){
        super(description);

        this.by = LocalDateTime.parse(by,
                    DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][dd/M/yyyy HHmm][d/M/yyyy HHmm][dd/MM/yyyy HHmm]"));

    }

    public String getDate(){
        return by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.US));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HHmm", Locale.US)) + ")";
    }
}
