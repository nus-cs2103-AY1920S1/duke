public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    // [Level-7] Converts task to String format to write to hard disk
    public String convertTaskToString() {
        return String.format("T | %s | %s", this.getStatus(), this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
