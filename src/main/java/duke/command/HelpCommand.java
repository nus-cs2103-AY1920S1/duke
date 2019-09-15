package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Creates a HelpCommand. Returns a list of commands.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    /**
     * Lists down commands that the user can type.
     *
     * @param t       TaskList to be appended.
     * @param ui      UI to interact with user.
     * @param storage Storage to read and write files.
     */
    public String execute(TaskList t, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
