public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public Task finish() {
        return new Task(this.description, true);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}

