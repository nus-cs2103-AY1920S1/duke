public class Task {
    String task;
    Boolean complete;

    String done = "[✓] ";
    String pending = "[✗] ";

    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    @Override
    public String toString() {
        if (this.complete) {
            return done + task;
        } else {
            return pending + task;
        }
    }

    public void markAsDone() {
        this.complete = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.toString());
    }

}