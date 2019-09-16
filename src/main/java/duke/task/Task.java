package duke.task;

public class Task {
    public String item;
    boolean isDone;

    // Constructor
    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return this.item;
    }

    public String saveTask() {
        return this.item;
    }

}
