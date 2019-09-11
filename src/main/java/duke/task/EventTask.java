package duke.task;

import duke.command.Commands;
import duke.command.IncorrectStatementException;

public class EventTask extends Task {
    private static final String DEFAULT_EVENT_ICON = "[E]";
    private static final String EVENT_TASK_STATEMENT = "at";
    private static final Commands EVENT_TASK_TYPE = Commands.EVENT;
    private String duration;

    /**
     * Constructor.
     * @param taskName - Name of task
     * @param duration - Deadline of task
     */
    public EventTask(String taskName, String duration) {
        super(taskName, DEFAULT_EVENT_ICON, EVENT_TASK_TYPE);
        this.duration = duration;
    }

    /**
     * Verifies that given statement complies with the required Deadline format.
     * @param statement - String containing expected statement
     * @throws IncorrectStatementException - thrown if the verification fails
     */
    public static void verifyTaskStatement(String statement) throws IncorrectStatementException {
        if (!statement.equals(EVENT_TASK_STATEMENT)) {
            throw new IncorrectStatementException(statement, EVENT_TASK_STATEMENT);
        }
    }

    /** Returns task duration. **/
    public String getDuration() {
        return this.duration;
    }

    /**
     * Returns a copy of the existing task.
     * @return new task
     */
    public Task getTaskCopy() {
        Task task = new EventTask(this.getName(), this.getDuration());
        if (this.isDone()) {
            task.markDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.duration + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask) {
            EventTask eventTask = (EventTask) obj;
            return (eventTask.getName().equals(this.getName())
                    && (eventTask.getTaskType().equals(this.getTaskType()))
                    && (eventTask.isDone() == this.isDone())
                    && (eventTask.getDuration().equals(this.getDuration())));
        }
        return false;
    }
}
