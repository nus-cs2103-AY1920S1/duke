public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.changeToFileFormat('T', description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
