public class Todo extends Task {

    protected String at;

    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
