public abstract class Task {
    private String title;
    protected String type;
    private boolean done;
    private String status;
    // varies according to the actual type, definitely includes topic, other optional included fields are date.
    protected String details;

    public Task(String s) {
        title = s;
        status = "\u2717";
    }

    public String getTitle() {
        return title;
    }

    public void markAsDone() {
        done = true;
        status = "\u2713";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, status, details);
    }
}
