import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String name, LocalDateTime time) {
        super(name, false);
        this.time = time;
    }

    public Event(String name, LocalDateTime deadLine, boolean done) {
        super(name, done);
        this.time = deadLine;
    }

    @Override
    public Event isDone() {
        return new Event(super.name, this.time, true);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, E, h:mm a");
        String s = "";
        if(done) {
            s = s + "[E][✓]";
        } else {
            s = s + "[E][✗]";
        }

        return s + " " + name + " (at: " + time.format(formatter) + ")";
    }
}
