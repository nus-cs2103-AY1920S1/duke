public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String encode() {
        return "deadline," + super.description + "," + super.isDone + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
