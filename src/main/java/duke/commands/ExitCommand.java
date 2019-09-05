package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.ui.UiInterface;

public class ExitCommand extends Command {

    /***
     * Class constructor.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Execute exit command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        ui.exit();
    }
}

