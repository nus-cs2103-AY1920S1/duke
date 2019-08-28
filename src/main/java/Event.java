import java.util.Scanner;

public class Event extends Task {
    private String info;
    private Date details;

    public Event(String description, String info) {
        super(description);
        this.details = new Date(info);
        this.info = info.trim();
    }

    public Event(String description, String checker, String info) {
        super(description, checker);
        this.details = new Date(info);
        this.info = info.trim();
    }

    public String getFormattedString() {
        return String.format("E | %s | %s | %s", super.getStatusIcon(), description, info);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + details + ")";
    }
}
