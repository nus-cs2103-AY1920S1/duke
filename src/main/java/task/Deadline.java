package task;

public class Deadline extends Task {
    private String ddl;

    public Deadline(String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
    }

    public Deadline(int status, String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
        isDone = (status == 1);
    }

    public String getDdl() {
        return ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl + ")";
    }

}