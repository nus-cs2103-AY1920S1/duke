import java.util.Calendar;

public class EventTask extends Task {
    protected Calendar time;

    public EventTask(String description, Calendar time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + String.format("%1$te of %1$tB, %1$tY, at %1$tH%1$tM hrs", this.time) + ")";
    }
}
