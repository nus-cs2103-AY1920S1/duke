public class Deadline extends Task {

    public Deadline(String s, String t) {
        super(s, t);
        this.label = "D";
    }

    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}
