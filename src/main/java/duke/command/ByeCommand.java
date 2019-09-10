package duke.command;

import duke.Duke;
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
     * Executes the command.
     *
     * @param duke Chat bot.
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     */
    @Override
    public String execute(Duke duke, Storage storage, TaskList taskList) {
        assert(isBye());
        return Ui.showByeMessage();
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
