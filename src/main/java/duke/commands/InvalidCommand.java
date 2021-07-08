package duke.commands;

import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.ui.UiInterface;

public class InvalidCommand extends Command {

    /**
     * Class constructor.
     */
    public InvalidCommand() {
        super(false);
    }

    /**
     * Execute invalid command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UiInterface ui) {
        ui.echoException(new DukeException());
    }
}
