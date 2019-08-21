public class Todo extends Task{
    public Todo(String description, String info) {
        super(Type.TODO, description, info);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
