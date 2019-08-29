import java.time.LocalDateTime;

public class EventTask extends Task {
    protected LocalDateTime time;

    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String formattedString() {
        return "E | " + super.formattedString() + " | " + time.getDayOfMonth() + "/" + time.getMonthValue() + "/"
                + String.format("%04d", time.getYear()) + " " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.getDayOfMonth() + " of " + time.getMonth() + ", "
                + time.getYear() + ", at " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + " hrs)";
    }
}
