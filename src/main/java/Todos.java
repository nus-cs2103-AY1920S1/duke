public class Todos extends Task {

    public Todos(String description) {
        super(description);
    }
    public Todos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String print() {
        if (this.isDone) {
            return "T @ 1 @ " + this.description;
        } else {
            return "T @ 0 @ " + this.description;
        }
    }
}
