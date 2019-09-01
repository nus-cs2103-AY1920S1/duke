public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + this.getStatusNumber() + " | " + this.description;
    }

    @Override
    public String toActionString() {
        return "[" + this.taskType + "][" + this.getStatusIcon() + "] " + this.description;
    }
}
