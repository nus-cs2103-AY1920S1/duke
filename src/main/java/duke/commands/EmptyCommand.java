package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.UiInterface;

public class EmptyCommand extends Command {

    /**
     * Class constructor.
     */
    public EmptyCommand() {
        super(false);
    }

    /**
     * Execute Empty command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        /** Empty Archives **/
        if (storage.emptyArchives()) {
            ui.echoDukeMessage("ARCHIVES EMPTIED");
        } else {
            ui.echoException(new DukeException("Failed to empty archives"));
        }
    }
}

