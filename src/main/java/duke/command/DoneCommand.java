package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    /**
     * Constructs a done command.
     *
     * @param params String containing task ID.
     */
    public DoneCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return Success message.
     * @throws DukeException If task ID is missing or invalid.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.params);
            Task doneTask = tasks.markDone(taskId);

            return Ui.showDoneTask(doneTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }
}
