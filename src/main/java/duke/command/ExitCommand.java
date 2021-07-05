package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an instruction to quit Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Quits Duke.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        String response = ui.showExitMessage();
        return new CommandResponse(response, super.isExit());
    }
}
