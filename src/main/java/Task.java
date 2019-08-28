public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name , boolean completionStatus) {
        this.name = name;
        this.isDone = completionStatus;
    }

    public String getCurrentStatus() {
        return ((isDone ? "[✓] " : "[✗] ")); //return tick or X symbols
    }

    public String getOverallStatus() {
        return getCurrentStatus() + name;
    }

    public String getName() {
    return name;
    }

    public void checkIfCompleted() {
        isDone = true;
    }
}