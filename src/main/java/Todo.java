public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveData() {
        return "T | " + super.saveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}