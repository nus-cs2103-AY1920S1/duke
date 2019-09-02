import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected String at;
    protected LocalDateTime date;

    public Events(String name, boolean completionStatus, String at) {
    public Events(String name, boolean completionStatus, LocalDateTime date) {
        super(name, completionStatus);
        this.at = at;
        this.date = date;
    }

    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + Description + "(at:" + at + ")";
        return "[E]" + getCurrentStatus() + Description + "(at:" + date.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone ? 1 : 0;
        return "events [" + myInt + "]" + Description + "/at:" + at;
        return "event [" + myInt + "]" + Description + "/at"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}