import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", done ? "v" : "x", name, dateTime.format(date).toString());
    }
}
