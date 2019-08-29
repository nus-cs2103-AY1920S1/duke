public class Deadline extends Task{
    private String timeOfDeadline;

    public Deadline(String s, String time) {
        super(s); //Call to Parent Class
        this.timeOfDeadline = time;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.timeOfDeadline + ")";
    }
}

