public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public static Todo outputAsTodo (String lineToRead) {
        return new Todo(lineToRead);
    }
    public String getDescription() {
        return super.getDescription();
    }
}
