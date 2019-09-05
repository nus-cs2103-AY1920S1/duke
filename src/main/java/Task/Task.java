package task;

public class Task {
    private String desc;
    private Boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return ("[" + getStatus() + "]" + " " + getDesc());
    }

}
