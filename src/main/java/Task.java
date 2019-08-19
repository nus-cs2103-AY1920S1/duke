public class Task {
    private String name;
    private boolean done;
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

