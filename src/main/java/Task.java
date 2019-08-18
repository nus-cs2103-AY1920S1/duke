public class Task {
    protected String name;
    protected boolean isDone;
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getName();
    }
}