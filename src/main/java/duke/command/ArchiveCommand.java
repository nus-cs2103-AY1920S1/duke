package duke.command;

import duke.Constants;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AddCommand class adds a task to the list.
 *
 * @author scwaterbear
 */
public class ArchiveCommand extends Command {

    /**
     * Class Constructor.
     */
    public ArchiveCommand(){}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        //want to discard tasks that are done
        List<Task> done = tasks.discardByTrait(x -> x.getStatusIcon().equals(Constants.TICK));
        Storage store = new Storage(Constants.ARCHIVE);
        try {
            store.saveDataToFile(done, true);
        } catch (IOException e) {
            return ui.showSaveError();
        }

        if (!isSaved(tasks, storage)) {
            return ui.showSaveError();
        }
        return ui.showArchive();
    }
}
