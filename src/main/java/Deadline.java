public class Deadline extends Task {
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    String saveFormat() {
        return "D|" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
