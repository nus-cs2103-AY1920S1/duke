public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toSave() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getBinaryStatus() {
        return (isDone ? "1" : "0");
    }

    public String getStatusIcon() {
        return (isDone ? "v" : "x"); //return tick or X symbols
    }

}