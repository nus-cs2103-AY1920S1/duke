package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class HelpCommand extends Command {
    /**
     * Prints the items in this TaskList.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui The Ui object passed from Duke.
     * @param storage The Storage object passed from Duke.
     * @return The response String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
