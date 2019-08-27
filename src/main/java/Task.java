
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean b){
        this.description = description;
        this.isDone = b;
    }
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getDescription(){
        return this.description;
    }
    public void markAsDone(){
        this.isDone = true;
    }
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
