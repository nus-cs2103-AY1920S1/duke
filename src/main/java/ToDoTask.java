public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String formattedString() {
        return "T | " + super.formattedString() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
