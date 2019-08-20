public class Task {
    private boolean done;
    private String name;
    private String type;

    public Task(String name) {
        this.done = false;
        this.name = name;
        this.type = type;
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
    public String gettype() {
        return name;
    }

    public void settype(String name) {
        this.name = name;
    }
}
