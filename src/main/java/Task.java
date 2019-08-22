public class Task {
    private boolean done;
    private String description;

    public Task(String s) {
        this.done = false;
        this.description = s;
    }

    public void mark() {
        if (!this.done) this.done = true;
    }

    public String toString() {
        if (this.done) {
            return "[\u2713] " + this.description;
        } else {
            return "[\u2718] " + this.description;
        }
    }
}