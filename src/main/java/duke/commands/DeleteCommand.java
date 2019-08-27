package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Create a delete command instance.
     * @param taskIndex index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Delete a task.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);
        ui.showDelete(task, taskList);
        storage.saveData(taskList);
    }
}
