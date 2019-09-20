package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command {
    /**
     * Constructs a delete command.
     *
     * @param params String containing task ID.
     */
    public DeleteCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param storage Storage instance.
     * @return Success message.
     * @throws DukeException If task ID is missing or invalid.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(this.params);
            Task deletedTask = tasks.deleteTask(taskId);

            return Ui.showDeletedTask(deletedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }
}
