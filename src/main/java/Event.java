import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime time;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    public Event(Status status, String taskName, LocalDateTime time) {
        super(status, taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")" + "\n";
    }

    public String toSaveString() {
        return "E|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|" +
                time.format(formatter);
    }

}
