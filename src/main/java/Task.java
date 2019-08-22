public class Task {
    protected String description;
    protected int listIndex;
    protected boolean isDone;

    public Task(String description, int listIndex) {
        this.description = description;
        this.listIndex = listIndex;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return description;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getListIndex() +  ". " + getStatusIcon() + " " + getDescription();
    }
}
