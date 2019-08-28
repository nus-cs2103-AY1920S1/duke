public class Todo extends Task {
    Todo(String[] description) {
        super(String.join(" ", description));
    }

    Todo(String description) {
        super(description);
    }

    @Override
    String saveFormat() {
        return "T|" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
