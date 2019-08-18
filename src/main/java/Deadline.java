public class Deadline extends Task {
    protected String extraInfo;

    public Deadline(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.extraInfo + ")";
    }
}
