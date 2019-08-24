import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    Event(String desc) {
        super(desc);
    }
    Event(String desc, boolean done) {
        super(desc, done);
    }
    Event(String desc, LocalDateTime startDate, LocalDateTime endDate) {
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    Event(String desc, LocalDateTime startDate, LocalDateTime endDate, boolean done) {
        super(desc, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getStartDate() + " to " + this.getEndDate() + ")";
    }
}
