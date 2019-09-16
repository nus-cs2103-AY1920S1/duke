package tasks;

import utils.DateTime;
import utils.Task;

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String taskName, DateTime by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    
    @Override
    public String getStorageString(){
        return "D | " + super.isDone + " | " + this.taskName + " | " + by.toString();
    }
}