package commands;

import logic.DukeList;
import logic.Storage;
import logic.Ui;

/**
 * Encapsulates command to exit and terminate the program.
 */
public class ExitCommand extends TaskCommands {

    /**
     * Overridden Method to exit the program.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) {
        ui.bye();
        System.exit(0);
    }
}
