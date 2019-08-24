public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveData() {
        String status = isDone ? "1" : "0";
        return "T \0 " + super.getSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
