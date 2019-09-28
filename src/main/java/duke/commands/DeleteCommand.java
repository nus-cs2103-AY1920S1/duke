package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
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
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        try {
            TaskList taskList = model.getTaskList();
            System.out.println(taskIndex);
            Task task = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);
            ui.showDelete(task, taskList);
            storage.saveData(model);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }
}
