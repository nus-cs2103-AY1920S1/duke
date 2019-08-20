public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //tick or X symbols
    }

    public String getStatusText() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    //setDone so that if the user accidentally marks something to done, it can be undone
    public void setDone(boolean done) {
        this.isDone = done;
    }

}
