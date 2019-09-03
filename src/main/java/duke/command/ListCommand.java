package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class when user issues a List command.
 */
public class ListCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Calls the ui to execute actions for a list command.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleList(userInput, tasks);
    }
}
