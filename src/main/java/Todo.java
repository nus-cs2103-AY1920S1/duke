public class Todo extends Task{
    public Todo(String description, String info) {
        super(description, info);
        super.type = Type.TODO;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
