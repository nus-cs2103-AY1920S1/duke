public class Task {
    public String todo;
    public boolean completed;

    public Task(String todo) {
        this.todo = todo;
        this.completed = false;
    }

    public String toString() {
        if (completed) {
            return "[✓] " + this.todo;
        } else {
            return "[✗] " + this.todo;
        }
    }
}
