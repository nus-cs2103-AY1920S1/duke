package duke.task;

import java.util.Date;
import java.util.Optional;

public class Deadline extends Task {
    /**
     * Constructor.
     *
     * @param name String
     * @param time Date
     */
    public Deadline(String name, Date time) {
        super(name, Optional.of(time), TaskType.DEADLINE, false);
    }

    /**
     * Constructor with isDone.
     *
     * @param name String
     * @param time Date
     * @param isDone boolean
     */
    public Deadline(String name, Date time, boolean isDone) {
        super(name, Optional.of(time), TaskType.DEADLINE, isDone);
    }

    @Override
    /**
     * Overrides toString method.
     *
     * @return String
     */
    public String toString() {
        return String.format("[D]%s %s (by: %s)", getStatus(), this.name, this.time.get());
    }
}
