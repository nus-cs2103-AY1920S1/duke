public class Task {
    private String description;
    boolean isDone;
    int doneIcon;

    public Task(String description) {
        this.description = description;
        isDone = false;
        doneIcon = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void markAsDone() {
        this.isDone = true;
        this.doneIcon = 1;
        System.out.println("Nice! I've marked this task as done:\n" +
                this);
    }

    public void updateDone() {
        this.isDone = true;
        this.doneIcon = 1;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }

    public String writeToFile() {
        return ("| " + doneIcon + " | " + this.description);
    }
}
