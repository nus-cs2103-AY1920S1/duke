public class Task {
    boolean done;
    String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public String getMark() {
        if (done) {
            return "✓";
        } else {
            return "✗";
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
