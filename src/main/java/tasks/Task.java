package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols
    }

    public boolean getStatus(){
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void changeStatus() {
        isDone = true;
    }

    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.getDescription();
    }

    public String writer() {
        return "This is a task";
    }
}
