package task;

public class Deadline extends Task {


    private String ddl;

    public Deadline(String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
    }

    /**
     * Deadline constructor.
     * @param status 0 or 1
     * @param desc description
     * @param ddl deadline
     */
    public Deadline(int status, String desc, String ddl) {
        super(desc);
        this.ddl = ddl;
        isDone = (status == 1);
    }


    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl + ")";
    }

}