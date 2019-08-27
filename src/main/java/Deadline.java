import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadLine;

    public Deadline(String name, LocalDateTime deadLine) {
        super(name, false);
        this.deadLine = deadLine;
    }

    public Deadline(String name, LocalDateTime deadLine, boolean done) {
        super(name, done);
        this.deadLine = deadLine;
    }

    @Override
    public Deadline changeToCompletedStatus() {
        return new Deadline(super.name, this.deadLine, true);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, E, h:mm a");
        String s = "";
        if(done) {
            s = s + "[D][✓]";
        } else {
            s = s + "[D][✗]";
        }

        return s + " " + name + " (by: " + deadLine.format(formatter) + ")";
    }

    public String toIndicationInsideFile() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s = "D | ";

        if(done) {
            s = s + "1 | ";
        } else {
            s = s + "0 | ";
        }

        return s + name + " | " + deadLine.format(formatter) + " " + (deadLine.getHour() * 100 + deadLine.getMinute());
    }
}