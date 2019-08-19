public class DeadlineTask extends Task {
    private static final String DEFAULT_DEADLINE_ICON = "[D]";
    private String deadLine;

    public DeadlineTask(String taskName, String deadLine) {
        super(taskName, DEFAULT_DEADLINE_ICON);
        this.deadLine = deadLine;
        System.out.println("  " + this);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadLine + ")";
    }
}
