public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markIsDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]"); //return tick or X symbols
    }

    public String toString(){
        return this.description;
    }

    public abstract String getType();

    public abstract String getDescription();

}
