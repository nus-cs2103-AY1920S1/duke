package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {

    /**
     * Executes the Help Command
     *
     * @param tasks The current tasks loaded
     * @param storage The storage file loaded
     * @param command The command that is being executed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        return Ui.showHelp();
    }
}
