public class Task {
    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols as + and - respectively
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("\t[%s] %s\n", getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "| " + getStatusIcon() + " | " + description + " ";
    }
}
