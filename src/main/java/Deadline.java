public class Deadline extends Task {
    public static final String REGEX = "/by";

    private String by;

    Deadline(String desc, String by) {
        super(desc.trim());
        this.by = by.trim();
    }
    @Override
    String getInitial() {
        return "D";
    }

    @Override
    String getAdditionalMessage() {
        return "(by: " + by + ")";
    }
}
