public class Event extends Task {
    public static final String REGEX = "/at";

    private String at;

    Event(String desc, String at) {
        super(desc.trim());
        this.at = at.trim();
    }
    @Override
    String getInitial() {
        return "E";
    }

    @Override
    String getAdditionalMessage() {
        return "(at: " + at + ")";
    }
}
