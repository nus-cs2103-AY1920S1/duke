package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.UiInterface;

public class ArchiveCommand extends Command {

    /**
     * Class constructor.
     */
    public ArchiveCommand() {
        super(false);
    }

    /**
     * Execute archive command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        if(storage.archiveTaskList(tasks)) {
            tasks.clearList();
            ui.echoMessage("Archiving Completed");
        }
        else {
            ui.echoException(new DukeException("Archiving Failed"));
        }
    }
}

