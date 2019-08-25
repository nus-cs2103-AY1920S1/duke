public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "Done" : "X");
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void setDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
