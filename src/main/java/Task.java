public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    //get the completion status of the task
    public String taskCompletion(){
        String status = getStatusIcon();
        return ("[" + status + "] " + description);
    }

    //Change task to completed;
    public void completed(){
        this.isDone = true;
    }

    //get the tick or cross
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }
}