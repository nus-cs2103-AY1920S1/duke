public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    protected String getTypeNameWithQuantifier() {
        return "a todo";
    }

    protected String toExportFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
