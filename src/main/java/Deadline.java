public class Deadline extends Task {
    protected String time;
    public Deadline (String description, String time) {
        super(description);
        this.time = time;
    }

    public String toFileString() {
        return "D||" + (this.isDone?"1||":"0||")  + this.description + "||" + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }

}
