public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[T][" + statusIcon + "] " + this.description;
    }
}
