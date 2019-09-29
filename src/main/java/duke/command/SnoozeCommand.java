package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.exception.DukeException;

/**
 * Represents a command to postpone ("snooze") a task.
 */
public class SnoozeCommand extends ModifyTaskCommand {

    public SnoozeCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Snoozes the task in the command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = getTaskById(tasks);
        task.snooze();
    }
}
