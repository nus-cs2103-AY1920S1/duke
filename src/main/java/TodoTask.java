public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
