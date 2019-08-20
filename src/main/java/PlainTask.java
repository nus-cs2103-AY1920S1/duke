public class PlainTask extends Task {
    public PlainTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
