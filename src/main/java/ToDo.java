public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
        this.label = "T";
    }

    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description;
    }
}
