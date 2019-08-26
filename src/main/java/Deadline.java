public class Deadline extends Task {
    protected String ddl;

    public Deadline(String description, String d) {
        super(description);
        this.ddl = d;
    }

    public String getDdl() {
        return ddl;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getDescription(), getDdl());
    }
}
