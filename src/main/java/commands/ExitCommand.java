package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public class ExitCommand extends Command {
    /**
     * This method is used to exit from application.
     *
     * @param tasks   the tasklist object to be used in this command
     * @param ui      the ui object to be used in this command
     * @param storage the storage object to be used in this command
     * @return duke response after exit
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks.getList() != null;
        storage.updateList(tasks.getList());
        return ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
