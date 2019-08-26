package seedu.duke.model;

public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.type = "";
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int status) {
        this.description = description;
        this.isDone = (status == 1 ? true : false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getType() { return type; }

    public void markAsDone() {
        setDone(true);
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✘]");
    }

    public String toTextFileString() {
        int status = (isDone ? 1 : 0);
        return type + "," + status + "," + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
