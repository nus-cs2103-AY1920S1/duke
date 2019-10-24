package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public class ListCommand extends Command {

    /**
     * This method is used to call for listing of task
     * in the current list.
     *
     * @param tasks   the TaskList object to be used in this command
     * @param ui      the Ui object to be used in this command
     * @param storage the Storage object to be used in this command
     * @return duke lists the tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks.getList() != null;
        storage.updateList(tasks.getList());
        return ui.printList(tasks.getList());
    }
}
