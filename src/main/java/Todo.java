public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    protected String getTypeNameWithQuantifier() {
        return "a todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
