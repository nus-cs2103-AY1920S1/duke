public class Task {
    protected static int total;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        total++;
        this.description = description;
        this.isDone = false;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal() {
        total--;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
