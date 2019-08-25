import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime date;


    public Deadline(String type, String name, LocalDateTime date) {
        super(type, name);
        this.date = date;
    }

    public Deadline(String type, String done, String name, LocalDateTime date) {
        super(type, done, name);
        this.date = date;
    }

    @Override
    public String fileFormat() {
        return String.format("%s | %s | %s | %s\n", type, done ? "1" : "0", name, dateTime.format(date).toString());
    }

    @Override
    public String toString() {

        return String.format("[D][%s] %s(by: %s)", done ? "v" : "x", name, dateTime.format(date).toString());

    }
}
