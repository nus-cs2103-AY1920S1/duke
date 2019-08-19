public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toString());
    }

    // Edit this to replace Y N with symbols
    public String getStatusIcon() {
        return (isDone ? "+" : " "); //return "+" if done " " otherwise
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.description);
    }
}