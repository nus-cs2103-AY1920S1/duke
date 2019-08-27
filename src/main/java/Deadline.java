public class Deadline extends Task {

    protected DateTime dateTime;

    public Deadline(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
    }

    @Override
    public String toSave() {
        return "D | " + super.getBinaryStatus() + " | " + super.description + " | " + time;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + dateTime + ")";
    }
}