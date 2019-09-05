package task;

public class Deadline extends Task {
    private String ddl;

    public Deadline(String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl + ")";
    }

}