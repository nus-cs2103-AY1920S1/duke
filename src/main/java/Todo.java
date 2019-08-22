public class Todo extends Task {
    public Todo(String description) {
        super(description.trim());
        super.tag = "[T]";
    }
}
