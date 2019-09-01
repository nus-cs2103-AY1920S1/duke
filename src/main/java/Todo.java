public class Todo extends Task {

    // the Todo subclass has one constructor
    public Todo(String item) {
        super(item);
    }

    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[T][" + "\u2713" + "] " + super.toString();
        } else {
            return "[T][" + "\u2718" + "] " + super.toString();
        }
    }

}
