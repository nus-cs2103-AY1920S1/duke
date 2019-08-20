public class Task{
    private String description;
    private boolean isDone;
    
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "✓" : "✗");
    }

    public String getTaskDescription(){
        return this.description;
    }

    public Task markAsDone(){
        Task completedTask = new Task(this.description);
        completedTask.isDone = true;
        return completedTask;
    }

    public String printTaskStatus(){
        return ("[" + this.getStatusIcon() + "] " + this.getTaskDescription());
    }
}
