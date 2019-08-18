
public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String task){
        taskName = task;
        isDone = false;
    }

    public String getTask(){
        return this.taskName;
    }

    public void completed(){
        this.isDone = true;
    }

    public boolean isDone(){
        return this.isDone;
    }


}
