package task;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        done = false;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
