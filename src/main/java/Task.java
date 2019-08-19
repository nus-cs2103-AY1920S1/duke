public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    protected Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public Task isDone() {
        return new Task(this.name, true);
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        this.done = true;
    }
}
