package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Lists all tasks in Duke's TaskList.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
