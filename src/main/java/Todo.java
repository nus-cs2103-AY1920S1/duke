public class Todo extends Task {
    Todo (String[] description) {
        super(String.join(" ", description));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
