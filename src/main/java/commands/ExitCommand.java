package commands;

import logic.*;

/**
 * Encapsulates command to exit and terminate the program
 */
public class ExitCommand extends Command {

    /**
     * Overridden Method to exit the program
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        System.exit(0);
    }
}
