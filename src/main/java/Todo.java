public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String encode() {
        return "todo," + super.description + "," + super.isDone + ",null";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
