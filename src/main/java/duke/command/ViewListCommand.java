package duke.command;

import duke.component.GuiResponse;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Command Class for view the task list.
 */
public class ViewListCommand extends Command {

    /**
     * Executes the operation of displaying task list.
     * @param taskList list of tasks.
     * @param storage storage to store inside hard disk.
     * @param ui ui for user interaction.
     * @return boolean indication of successful or unsuccessful running of command.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {

        return GuiResponse.getTaskListInString(taskList);
    }
}
