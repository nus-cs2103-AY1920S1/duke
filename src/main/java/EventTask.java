public class EventTask extends Task {
    private static final String DEFAULT_EVENT_ICON = "[E]";
    private static final String EVENT_TASK_STATEMENT = "at";
    private static final String EVENT_TASK_TYPE = "Event";
    private String duration;

    public EventTask(String taskName, String duration) {
        super(taskName, DEFAULT_EVENT_ICON, EVENT_TASK_TYPE);
        this.duration = duration;
    }

    public static void verifyTaskStatement(String statement) throws IncorrectStatementException {
        if (!statement.equals(EVENT_TASK_STATEMENT)) {
            throw new IncorrectStatementException(statement, EVENT_TASK_STATEMENT);
        }
    }

    public String getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.duration + ")";
    }
}
