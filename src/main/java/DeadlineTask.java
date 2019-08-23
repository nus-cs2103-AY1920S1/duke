public class DeadlineTask extends Task {
    private static final String DEFAULT_DEADLINE_ICON = "[D]";
    private static final String DEADLINE_TASK_STATEMENT = "by";
    private String deadLine;

    public DeadlineTask(String taskName, String deadLine) {
        super(taskName, DEFAULT_DEADLINE_ICON);
        this.deadLine = deadLine;
    }

    public static void verifyTaskStatement(String statement) throws IncorrectStatementException {
        if (!statement.equals(DEADLINE_TASK_STATEMENT)) {
            throw new IncorrectStatementException(statement, DEADLINE_TASK_STATEMENT);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadLine + ")";
    }
}
