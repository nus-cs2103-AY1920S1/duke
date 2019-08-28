public class Task {
    String task;
    Boolean complete;

    String done = "[✓] ";
    String pending = "[✗] ";

    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    public Task(String task, boolean isPending) {
        this.task = task;
        this.complete = isPending;
    }

    public String toStringForFile () {
        return "wrong because of this";
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