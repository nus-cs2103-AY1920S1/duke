package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Creates an ExitCommand. It ends the programme and stores user's tasks.
 */
public class ExitCommand extends Command {
    /**
     * Saves the tasks into storage and ends the programme.
     *
     * @param t TaskList to be appended.
     * @param ui UI to interact with user.
     * @param storage Storage to read and write files.
     */
    public String execute(TaskList t, Ui ui, Storage storage) {
        storage.save(t.tasks);
        super.exit = true;
        return ui.showExitMessage(t.tasks);
    }
}
