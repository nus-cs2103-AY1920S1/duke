import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String desc) {
        super(desc);
    }
    Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    public LocalDateTime getDateBy() {
        return by;
    }

    public void setDateBy(LocalDateTime by) {
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateBy() + ")";
    }
}
