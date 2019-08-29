
public class Task {
    protected boolean completed;
    protected int index;
    protected String name;
    protected static int count = 0;

    public Task(String n) {
        count++;
        this.name = n;
        this.index = count;
        completed = false;
    }

    public Task(String n, boolean completed) {
        count++;
        this.name = n;
        this.index = count;
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public void complete() {
        this.completed = true;
    }
}
