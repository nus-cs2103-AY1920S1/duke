public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, String checker) {
        this.description = description;
        this.isDone = (checker.equals("1")) ? true : false;
    }

    public void markAsDone() {
        isDone = true;
    }

    protected String getStatusIcon() {
        return isDone ? "1" : "0";
    }



    public abstract String getFormattedString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
