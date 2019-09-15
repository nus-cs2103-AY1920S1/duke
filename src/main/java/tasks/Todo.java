package tasks;

import utils.Task;

public class Todo extends Task {
    protected String by;

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String getStorageString(){
        return "T | " + super.isDone + " | " + this.taskName;
    }
}