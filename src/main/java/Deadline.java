import java.util.Date;

class Deadline extends Task {
    private Date by;

    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), Duke.dateFormatter.format(this.by));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Duke.dateFormatter.format(this.by));
    }
}
