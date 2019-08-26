package duke.task;

import java.util.Date;
import java.util.Optional;

public class Deadline extends Task {
    /**
     * Constructor.
     */
    public Deadline(String name, Date time) {
        super(name, Optional.of(time), TaskType.DEADLINE, false);
    }

    /**
     * Constructor with isDone.
     */
    public Deadline(String name, Date time, boolean isDone) {
        super(name, Optional.of(time), TaskType.DEADLINE, isDone);
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        return String.format("[D]%s %s (by: %s)", getStatus(), this.name, this.time.get());
    }
}
