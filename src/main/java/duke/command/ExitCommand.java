package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends TaskCommand {
    private static String bye = "Bye. Hope to see you again soon!\n";

    public boolean isExit() {
        return true;
    }

    /**
     * Executes exit command.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when execution encounters problem
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getList());
        return ui.print(bye);
    }
}
