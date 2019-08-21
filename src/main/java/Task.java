public abstract class Task{
    protected String description;
    protected boolean isDone;
    protected String taskType;

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

    public abstract Task markAsDone() throws DukeException;

    public String getTaskType(){
        return this.taskType;
    }

    public abstract String getTaskStatus();
}
