package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Create a done command instance.
     * @param taskIndex index of the task to be marked as done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Mark a task as done.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(this.taskIndex);
        task.markAsDone();
        ui.showDone(task);
        storage.saveData(taskList);
    }
}
