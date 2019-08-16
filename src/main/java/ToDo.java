public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][\u2713] " + this.description;
        } else {
            return "[T][\u2718] " + this.description;
        }
    }
}
