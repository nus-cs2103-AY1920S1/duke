package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String desc) {
        super(desc);
    }
    public Deadline(String desc, boolean done) {
        super(desc, done);
    }
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }
    public Deadline(String desc, LocalDateTime by, boolean done) {
        super(desc, done);
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
