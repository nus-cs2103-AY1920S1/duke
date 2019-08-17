public abstract class Task {
    private int index;
    private String title;
    protected String type;
    private boolean done;
    private String status;
    // varies according to the actual type, definitely includes topic, other optional included fields are date.
    protected String details;

    public Task(int i, String s) {
        index  = i;
        title = s;
        status = "\u2717";
    }

    public String getTitle() {
        return title;
    }

    /**
     * @return detailed description of the task, including the title, type, done status, etc.
     */
    public String getDescription() {
        return String.format("[%s][%s] %s", type, status, details);
    }

    public void markAsDone() {
        done = true;
        status = "\u2713";
    }

    @Override
    public String toString() {
        return String.format("%d.%s", index, getDescription());
    }
}
