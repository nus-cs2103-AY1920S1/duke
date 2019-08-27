import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")" + "\n";
    }

}
