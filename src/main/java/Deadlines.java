public class Deadlines extends Task {

    protected DateTime date;


    public Deadlines(String description, DateTime date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public DateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + date + ")";
    }
}
