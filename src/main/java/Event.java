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
    public Event changeToCompletedStatus() {
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

    public String toIndicationInsideFile() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s = "E | ";

        if(done) {
            s = s + "1 | ";
        } else {
            s = s + "0 | ";
        }

        return s + name + " | " + time.format(formatter) + " " + (time.getHour() * 100 + time.getMinute());
    }
}