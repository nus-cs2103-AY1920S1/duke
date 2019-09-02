package duke.lib.task;

import duke.lib.common.Time;

public class Event extends Task {
    protected Time time;

    /**
     * Constructor for event object.
     *
     * @param task the name of the task.
     * @param time the time at which it will be held at.
     */
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
