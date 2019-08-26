public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int id;
    protected TaskType type;

    public Task(String description, int id){
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    protected TaskType getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public String getDate(){
        return "";
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
