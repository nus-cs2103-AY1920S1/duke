public class TodoTask extends Task {

    /**
     * Creates a TodoTask with a given description.
     * @param description Task description
     */
    TodoTask(String description) {
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
