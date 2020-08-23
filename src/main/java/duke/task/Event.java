package duke.task;

import java.util.Date;
import java.util.Optional;

public class Event extends Task {
    /**
     * Constructor.
     *
     * @param name String
     * @param time Date
     */
    public Event(String name, Date time) {
        super(name, Optional.of(time), TaskType.EVENT, false);
    }
    
    /**
     * Constructor with isDone.
     *
     * @param name String
     * @param time Date
     * @param isDone boolean
     */
    public Event(String name, Date time, boolean isDone) {
        super(name, Optional.of(time), TaskType.EVENT, isDone);
    }

    @Override
    /**
     * Overrides toString method.
     *
     * @return String
     */
    public String toString() {
        return String.format("[E]%s %s (at: %s)", getStatus(), this.name, this.time.get());
    }
}
