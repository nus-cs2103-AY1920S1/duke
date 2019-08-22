class Task {

    boolean isDone = false;
    String taskName;

    Task(String taskName) {
        this.taskName = taskName;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [✓] " + taskName);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[✓] " + taskName;
        }
        return "[✗] " + taskName;
    }
}
