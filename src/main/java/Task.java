package DukePkg;
public class Task {
    private boolean done_ = false;
    private String task_ = "";
    public String type = "";

    public Task(String task) {
        task_ = task;
    }

    public boolean isDone() {
        return done_;
    }

    public String getTask() {
        return task_;
    }

    public void markDone() {
        done_ = true;
    }

    public String getSymbol() {
        if(isDone()) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "] " + task_;
    }
}
