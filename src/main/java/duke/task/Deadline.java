package duke.task;

import java.util.Date;

public class Deadline extends Task {
    Date time;

    /**
     * Constructor.
     */
    public Deadline(String name, Date time) {
        super(name);
        this.time = time;
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("[D]%s %s (by: %s)", status, this.name, this.time);
    }
}
