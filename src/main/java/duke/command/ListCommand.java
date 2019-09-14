package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends TaskCommand {
    private static final String outputListTask = "Here are the tasks in your list:\n";

    public boolean isExit() {
        return false;
    }

    /**
     * Lists out all the tasks currently in the list.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when execution encounters problem
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder(outputListTask);
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append((i + 1) + "." + taskList.getTask(i + 1) + "\n");
        }
        return ui.print(sb.toString());
    }
}
