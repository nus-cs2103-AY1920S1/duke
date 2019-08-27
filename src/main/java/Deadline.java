public class Deadline extends Task {

    protected DateTime dateTime;
    protected String timeInFile;

    public Deadline(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
        this.timeInFile = dateTimeString;
    }

    @Override
    public String toSave() {
        return "D | " + super.getBinaryStatus() + " | " + super.description + " | " + timeInFile;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + dateTime + ")";
    }
}