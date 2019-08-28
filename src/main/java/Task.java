public class Task {
    private boolean isDone;
    protected String description;
    protected String time;
    protected String label;

    public Task(String s) {
        this.isDone = false;
        this.description = s;
    }

    public Task(String s, String t) {
        this.isDone = false;
        this.description = s;
        this.time = t;
    }

    public String getLabel() {
        return label;
    }

    public String getTime() {
        if (this.time != null) {
            return time;
        } else {
            return "";
        }
    }

    public int getStatus() {
        return this.isDone ? 1 : 0;
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        if (!isDone) this.isDone = true; //update status of task
    }

    protected String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

}