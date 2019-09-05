package duke.commands;

import duke.core.TaskList;
import duke.core.Storage;
import duke.ui.UiInterface;

public class ListCommand extends Command {

    /***
     * Class constructor.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Execute list command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        ui.echoList(tasks);
    }
}
