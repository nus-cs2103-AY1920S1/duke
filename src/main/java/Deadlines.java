public class Deadlines extends Task {
    private String info;

    public Deadlines(String description, String info) {
        super(description);
        this.info = info;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + info + ")";
    }
}
