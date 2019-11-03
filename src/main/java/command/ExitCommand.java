package command;

import main.Archive;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * An ExitCommand object to deal with requests to exit.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand to deal with the 'bye' command.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the command to allow the program to exit.
     *
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        String res = ui.getGoodbyeMessage();
        super.canExit();
        return res;
    }
}
