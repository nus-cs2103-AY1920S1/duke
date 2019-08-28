public class Deadlines extends Task {

    protected String by;

    public Deadlines(String name, boolean completionStatus , String by) {
        super(name,completionStatus);
        this.by = by;
    }
    @Override
    public String getOverallStatus() {
        return "[D]" + getCurrentStatus() + name + "(by:" + by + ")";
    }
}
