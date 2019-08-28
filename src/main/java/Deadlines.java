import java.util.Scanner;

public class Deadlines extends Task {
    private String info;
    private Date details;

    public Deadlines(String description, String info) {
        super(description);
        this.details = new Date(info);
        this.info = info.trim();
    }

    public Deadlines(String description, String checker, String info) {
        super(description, checker);
        this.details = new Date(info);
        this.info = info.trim();
    }

    public String getFormattedString() {
        return String.format("D | %s | %s | %s", super.getStatusIcon(), description, info);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + details + ")";
    }
}
