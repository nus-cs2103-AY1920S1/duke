package tasks;

import utils.DateTime;
import utils.Task;

public class Event extends Task {
    protected DateTime at;

    public Event(String taskName, DateTime at) {
        super(taskName);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
    
    @Override
    public String getStorageString() {
        return "E | " + super.isDone + " | " + this.taskName + " | " + at.toString();
    }
}