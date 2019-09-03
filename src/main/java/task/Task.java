package task;
import formatter.TimeFormatter;

public class Task {
    String name;
    boolean completed;
    String type;


    public Task(String taskInput, boolean complete) {
        name = taskInput;
        completed = complete;
        type = null;
    }

    public String getName() {
        return name;
    }
    public String getType() { return type;}
    public boolean getDoneStatus() { return completed;}

    //return the String of having completed the current task
    //Change the completed variable
    public Task taskComplete() {
        completed = true;
        return this;
    }

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name;
        } else {
            return "[" + type + "]" + "[\u2718] " + name;
        }

    }


}
