import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDateTime date;

    public Deadlines(String name, boolean completionStatus , LocalDateTime date) {
        super(name,completionStatus);
        this.date = date;
    }
    @Override
    public String getOverallStatus() {
        return "[D]" + getCurrentStatus() + Description + "(by:" + date.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone ? 1 : 0;
        return "deadline [" + myInt + "]" + Description + "/by"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
