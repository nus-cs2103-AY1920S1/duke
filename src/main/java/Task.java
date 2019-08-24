import java.io.Serializable;

class Task implements Serializable{
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2717";

    private String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[" + TICK + "] " + this.task;
        } else {
            return "[" + CROSS + "] " + this.task;
        }
    }
}