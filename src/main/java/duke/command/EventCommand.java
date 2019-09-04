package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class when user issues a Deadline command.
 */
public class EventCommand extends Command {

    /**
     * Input from user.
     */
    private String userInput;

    /**
     * Class constructor that assigns instance with user input.
     * @param userInput Input from user.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Calls the ui to execute actions for a execute task.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.handleEvent(userInput, tasks, storage);
    }
}
