public class Deadline extends Task   {
    private String deadlineTime;

    public Deadline(String name) {
        super(name);
    }

    public Deadline(String name, String dT) {
        super(name);
        this.deadlineTime = dT;
    }

    public String toString() {
        return "D|" + super.toString().trim() + "|" + deadlineTime.trim();
    }
}
