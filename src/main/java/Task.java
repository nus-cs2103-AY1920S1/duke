public class Task {
    private int index;
    private String details;
    private boolean done;
    private String status;

    public Task(int i, String s) {
        index  = i;
        details = s;
        status = "\u2717";
    }

    public int getIndex() {
        return index;
    }

    public String getDetails() {
        return details;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        done = true;
        status = "\u2713";
    }

    @Override
    public String toString() {
        return String.format("%d.[%s] %s", index, status, details);
    }
}
