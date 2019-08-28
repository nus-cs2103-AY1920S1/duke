public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
        this.type = "T";
        this.dateTime = "";
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
        this.dateTime = "";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
