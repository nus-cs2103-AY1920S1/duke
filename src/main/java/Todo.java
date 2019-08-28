public class Todo extends Task {
    String type;

    public Todo(String taskName) {
        super(taskName);
        type = "todo";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
