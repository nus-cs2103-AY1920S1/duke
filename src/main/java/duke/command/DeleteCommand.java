package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.exception.DukeException;

public class DeleteCommand extends ModifyTaskCommand {

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes a task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = getTaskById(tasks);
        tasks.deleteTask(task);
    }
}
