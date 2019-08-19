public class Deadline extends Task {
    private String deadLine;

    public Deadline(String name, String deadLine) {
        super(name, false);
        this.deadLine = deadLine;
    }

    public Deadline(String name, String deadLine, boolean done) {
        super(name, done);
        this.deadLine = deadLine;
    }

    @Override
    public Deadline isDone() {
        return new Deadline(super.name, this.deadLine, true);
    }

    @Override
    public String toString() {
        String s = "";
        if(done) {
            s = s + "[D][✓]";
        } else {
            s = s + "[D][✗]";
        }

        return s + " " + name + " (by: " + deadLine + ")";
    }
}
