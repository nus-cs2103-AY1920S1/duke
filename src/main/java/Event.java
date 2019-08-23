public class Event extends Task {
    public static final String REGEX = "/at";
    public static final String INITIAL = "E";

    private String at;

    Event(String desc, String at) {
        super(desc.trim());
        this.at = at.trim();
    }
    @Override
    String getInitial() {
        return INITIAL;
    }

    @Override
    String getDate() {
        return at;
    }

    @Override
    String getPrefix() {
        return "at: ";
    }
}
