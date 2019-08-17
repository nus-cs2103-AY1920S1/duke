package Task;

abstract public class Task {
    String description, timing;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, String timing) {
        this(description);
        this.timing = timing;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    //setDone so that if the user accidentally marks something to done, it can be undone
    public void setDone(boolean done) {
        this.isDone = done;
    }

    abstract public String getStatusText();
}
