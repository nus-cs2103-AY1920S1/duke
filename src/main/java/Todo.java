public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + '\n';
    }

    public String childClass() {
        return "todo";
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
