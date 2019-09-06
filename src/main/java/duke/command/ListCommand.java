package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    /**
     * List out all the duke.tasks currently in the list.
     *
     * @param taskList The list of duke.tasks maintained in duke.Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the duke.tasks
     * @throws DukeException when execution encounters problem
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder("Here are the duke.tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append("\n" + (i + 1) + "." + taskList.getTask(i + 1));
        }
        ui.print(sb.toString());
    }
}
