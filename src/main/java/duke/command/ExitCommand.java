package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

/**
 * Command Class for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Executing the operation of exiting Duke program.
     * @param taskList list of tasks.
     * @param storage storage to store inside hard disk.
     * @param ui ui for user interaction.
     * @return boolean indication of successful or unsuccessful running of command.
     * @throws IOException when error occurs while writing to hard disk.
     */
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws IOException {

        storage.save(taskList);
        ui.showGoodByeScreen();

        return true;
    }
}
