public class Events extends Task {

    protected String at;

    public Events(String name, boolean completionStatus, String at) {
        super(name, completionStatus);
        this.at = at;
    }

    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + Description + "(at:" + at + ")";
    }

    @Override
    public String encodeForStorage() {
        return "events" + getCurrentStatus() + Description + "(at:" + at + ")";
    }
}