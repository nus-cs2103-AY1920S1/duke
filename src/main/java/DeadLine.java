public class DeadLine extends Task {

    public DeadLine(String s, String t) {
        super(s, t);
        this.label = "D";
    }

    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}
