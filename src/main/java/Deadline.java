public class Deadline extends Task{
    protected String byDateTime;

    public Deadline(String description, String byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    public String getByDateTime() {
        return byDateTime;
    }

    @Override
    public String toString() {
        String str = "["
                + "D"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + " (by: "
                + this.getByDateTime()
                + ")";
        return str;
    }
}