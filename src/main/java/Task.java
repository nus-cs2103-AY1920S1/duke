public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Character getStatusIcon() {
        return (isDone ? 'V' : 'X'); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return description;
    }

}
