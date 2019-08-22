public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markAsDone() {
        done = true;
        System.out.println("Nice! I've marked this task as done: \n" +
                "[✓] " + taskName);
    }

    public String toString() {
        if (done) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
