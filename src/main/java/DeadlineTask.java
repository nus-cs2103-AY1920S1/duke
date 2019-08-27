import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    protected LocalDateTime time;

    public DeadlineTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String formattedString() {
        return "D | " + super.formattedString() + " | " + time.getDayOfMonth() + "/" + time.getMonthValue() + "/"
                + time.getYear() + " " + String.format("%02d", time.getHour()) + String.format("%02d", time.getMinute())
                + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + time.getDayOfMonth() + " of " + time.getMonth() + ", "
                + time.getYear() + ", at " + String.format("%02d", time.getHour())
                + String.format("%02d", time.getMinute()) + " hrs)";
    }
}
