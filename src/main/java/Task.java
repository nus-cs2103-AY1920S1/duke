public class Task {
    private String name;
    private boolean done = false;

    Task(String name) {
        this.name = name;
    }

    void setDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        return ("[" + (this.done ? "✓" : "✗") + "]" + " " + this.name);
    }
}
