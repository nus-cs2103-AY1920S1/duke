public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, String checker) {
        super(description, checker);
    }

    public String getFormattedString() {
        return String.format("T | %s | %s", super.getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
