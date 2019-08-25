package duke.task;

import java.util.Optional;

public class Event extends Task {
    /**
     * Constructor.
     */
    public Event(String name, String time) {
        super(name, Optional.of(time), TaskType.EVENT, false);
    }
    
    /**
     * Constructor with isDone.
     */
    public Event(String name, String time, boolean isDone) {
        super(name, Optional.of(time), TaskType.EVENT, isDone);
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        return String.format("[E]%s %s (at: %s)", getStatus(), this.name, this.time.get());
    }
}
