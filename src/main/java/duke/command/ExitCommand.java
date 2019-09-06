package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    /**
     * Execute exit duke.command.
     *
     * @param taskList The list of duke.tasks maintained in duke.Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the duke.tasks
     * @throws DukeException when execution encounters problem
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getList());
        ui.print("Bye. Hope to see you again soon!");
    }
}
