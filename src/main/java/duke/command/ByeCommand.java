package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to end chat bot.
 */
public class ByeCommand extends Command {
    /**
     * Constructs an ByeCommand object.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Overrides execute method in abstract superclass.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {

    }

    /**
     * Flags command as a command to end chat bot.
     *
     * @return True, indicating that command is indeed a command to end chat bot.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
