package slave.task;

public abstract class Task {

    private String description;
    private boolean isDone;
    private int id;
    TaskType type;

    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    public void decrementId() {
        this.id--;
    }

    public int getId() {
        return this.id;
    }

    public TaskType getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public String getDate() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "Done" : "X");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
