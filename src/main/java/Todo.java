public class Todo extends Task {

    public Todo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T][" + "\u2713" + "] " + super.toString();
        } else {
            return "[T][" + "\u2718" + "] " + super.toString();
        }
    }

}
