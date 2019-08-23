public class Deadline extends Task {
    public static final String REGEX = "/by";
    public static final String INITIAL = "D";

    private String by;

    Deadline(String desc, String by) {
        super(desc.trim());
        this.by = by.trim();
    }
    @Override
    String getInitial() {
        return INITIAL;
    }

    @Override
    String getDate() {
        return by;
    }

    @Override
    String getPrefix() {
        return "by: ";
    }
}
