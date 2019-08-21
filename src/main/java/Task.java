public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected String status;

    public Task(String description, int index, String status) {
        this.description = description;
        this.index = index;
        this.status = status;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }
    public String getIndex() {
        return this.index + ".";
    }
    public String getStatus() {
        return "[" + this.status + "]";
    }
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "]" + this.description;
    }
}
