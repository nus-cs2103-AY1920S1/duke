package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ByeCommand extends Command {
    /**
     * Terminates the program.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui       The Ui object passed from Duke.
     * @param storage  The Storage object passed from Duke.
     * @return The response String.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showBye();
    }
}