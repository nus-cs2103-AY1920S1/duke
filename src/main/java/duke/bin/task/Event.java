package duke.bin.task;

import duke.bin.common.Time;

public class Event extends Task {
    protected Time time;

    public Event(String task, Time time) {
        super(task);
        this.time = time;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
