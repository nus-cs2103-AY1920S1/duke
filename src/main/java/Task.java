import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Task {
    public static final DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
    protected String name;
    protected boolean done;


    public Task() {

    }

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String done() {
        this.done = true;
        return String.format("Nice! I've marked this task as done:\n  [%s] %s", "v", name);
    }

    public String toString() {
        return String.format("[T][%s] %s", done ? "v" : "x", name);
    }
}
