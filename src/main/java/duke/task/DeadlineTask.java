package duke.task;

import duke.command.Commands;
import duke.command.IncorrectStatementException;

public class DeadlineTask extends Task {
    private static final String DEFAULT_DEADLINE_ICON = "[D]";
    private static final String DEADLINE_TASK_STATEMENT = "by";
    private static final Commands DEADLINE_TASK_TYPE = Commands.DEADLINE;
    private String deadLine;

    /**
     * Constructor.
     * @param taskName - Name of task
     * @param deadLine - Deadline of task
     */
    public DeadlineTask(String taskName, String deadLine) {
        super(taskName, DEFAULT_DEADLINE_ICON, DEADLINE_TASK_TYPE);
        this.deadLine = deadLine;
    }

    /**
     * Verifies that given statement complies with the required Deadline format.
     * @param statement - String containing expected statement
     * @throws IncorrectStatementException - thrown if the verification fails
     */
    public static void verifyTaskStatement(String statement) throws IncorrectStatementException {
        if (!statement.equals(DEADLINE_TASK_STATEMENT)) {
            throw new IncorrectStatementException(statement, DEADLINE_TASK_STATEMENT);
        }
    }

    /** Returns the task deadline. **/
    public String getDeadline() {
        return this.deadLine;
    }

    /**
     * Returns a copy of the existing task.
     * @return new task
     */
    public Task getTaskCopy() {
        Task task = new DeadlineTask(this.getName(), this.getDeadline());
        if (this.isDone()) {
            task.markDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadLine + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) obj;
            return (deadlineTask.getName().equals(this.getName())
                    && (deadlineTask.getTaskType().equals(this.getTaskType()))
                    && (deadlineTask.isDone() == this.isDone())
                    && (deadlineTask.getDeadline().equals(this.getDeadline())));
        }
        return false;
    }
}
