
public class Task {
    private String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                this.getStatusIcon() + " " + this.description);
    }

    public String getDescription() {
        return this.description;
    }
}
