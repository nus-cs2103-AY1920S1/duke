public class Deadline extends Task {
    String dateAndTime;


    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[D][\u2713] " + this.description + " (by: " + this.dateAndTime + ")";
        } else {
            return "[D][\u2718] " + this.description + " (by: " + this.dateAndTime + ")";
        }
    }
}