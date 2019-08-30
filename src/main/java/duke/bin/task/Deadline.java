package duke.bin.task;

import duke.bin.common.Time;


public class Deadline extends Task {
    protected Time time;

    /**
     * Constructs a deadline object.
     *
     * @param task the name of the task.
     * @param time the time by which it should be completed.
     */
    public Deadline(String task, Time time) {
        super(task);
        this.time = time;
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}

