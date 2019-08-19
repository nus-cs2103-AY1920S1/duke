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

    public String getName() {
        return name;
    }

    public Task isDone() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
        String s = "";
        if(done) {
            s = s + "[✓]";
        } else {
            s = s + "[✗]";
        }

        return s + " " + name;
    }
}

