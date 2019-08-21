public class Task {

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatus() {
        String icon = (isDone ? "\u2713" : "\u2718"); //tick or X symbol
        return String.format("[%s] %s", icon, this.description);
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

}