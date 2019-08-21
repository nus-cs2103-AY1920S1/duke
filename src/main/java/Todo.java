public class Todo extends Task {
    String type;

    public Todo(String task_name) {
        super(task_name);
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
