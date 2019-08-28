public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.typeOfTask = "T";
    }

    public String toString() {
        return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
