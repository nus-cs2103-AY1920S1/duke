public class Task {
    protected String description;
    protected boolean isDone;
    protected String status;

    public Task(String description, String status) {
        this.description = description;
        this.status = status;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return "[" + this.status + "]";
    }
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "]" + this.description;
    }
}
