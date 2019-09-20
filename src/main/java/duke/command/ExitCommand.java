package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {
    /**
     * Executes Exit command to close Duke.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui.
     * @param storage Current Storage.
     * @throws DukeException Never.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.append(ui.getBye());
        Platform.exit();
    }
}
