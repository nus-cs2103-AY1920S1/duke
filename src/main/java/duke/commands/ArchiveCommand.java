package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.UiInterface;

public class ArchiveCommand extends Command {
    private String archiveName;

    /**
     * Class constructor.
     */
    public ArchiveCommand(String archiveName) {
        super(false);
        this.archiveName = archiveName;
    }

    /**
     * Execute archive command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        if (storage.archiveTaskList(tasks, archiveName)) {
            tasks.clearList();
            ui.echoMessage("ARCHIVING COMPLETE");
        } else {
            ui.echoException(new DukeException("Failed to archive tasks"));
        }
    }
}

