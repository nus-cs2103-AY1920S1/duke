public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone ? "[\u2713]" : "[\u2718]";
        return doneSymbol + " " + description;
    }
}
