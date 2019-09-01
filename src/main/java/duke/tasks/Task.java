package duke.tasks;

public class Task {

    String taskName;
    boolean isDone;

    public Task(String taskName, boolean isDone){
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setDone(boolean done){
        isDone = done;
    }

    public boolean contains(String toSearch){
        return taskName.contains(toSearch);
    }

    public String toFile(){
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " |" + taskName;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[T][" + mark + "]" + taskName;
    }
}
