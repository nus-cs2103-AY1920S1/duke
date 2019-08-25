package command;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    /**
     * Execute exit command.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when execution encounters problem
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getList());
        ui.print("Bye. Hope to see you again soon!");
    }
}
