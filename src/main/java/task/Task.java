package task;

public class Task {
    private String desc;
    Boolean isDone;

    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getDesc() {
        return this.desc;
    }

    private String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getDone() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return ("[" + getStatus() + "]" + " " + getDesc());
    }

}
