public class Deadline extends Task   {
    private String deadlineTime;

    public Deadline(String name) {
        super(name);
    }

    public Deadline(String name, String dT) {
        super(name);
        formatDeadlineTime(dT);
    }

    public void formatDeadlineTime(String dT) {
        String format = dT.substring(0,2) + ":" + dT.substring(2);
        this.deadlineTime = format;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + deadlineTime + ")";
    }
}
