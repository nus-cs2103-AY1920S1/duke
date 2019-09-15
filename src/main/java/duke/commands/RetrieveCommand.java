package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.UiInterface;

public class RetrieveCommand extends Command {
    private String archiveName;

    /**
     * Class constructor.
     */
    public RetrieveCommand(String archiveName) {
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
        try {
            TaskList retrievedTaskList = new TaskList(storage.retrieveArchivedTaskList(archiveName));
            tasks.loadData(retrievedTaskList.getTasks());
            ui.echoDukeMessage("RETRIEVED ARCHIVE");
        } catch (DukeException e) {
            ui.echoException(e);
        } catch (Exception e) {
            ui.echoException(new DukeException("Failed to retrieve archive"));
        }
    }
}

