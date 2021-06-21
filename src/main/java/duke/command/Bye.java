package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 */
public class Bye extends Command {

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        return ui.bye();
    }
}
