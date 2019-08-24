import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return "D";
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }


    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %d/%d/%d %02d%02d)",
                this.getType() ,isDone ? "\u2713": "\u2718",
                this.taskName, this.dateTime.getDayOfMonth(), this.dateTime.getMonthValue(), this.dateTime.getYear(),
                this.dateTime.getHour() ,this.dateTime.getMinute());
    }
}
