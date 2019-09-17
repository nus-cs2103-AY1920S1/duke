package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an instruction to list all tasks.
 */
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
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        String response = ui.showListMessage(taskList);
        return new CommandResponse(response, super.isExit());
    }
}
