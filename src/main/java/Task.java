public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[✓] " + name;
        } else {
            return "[✗] " + name;
        }
    }
}
