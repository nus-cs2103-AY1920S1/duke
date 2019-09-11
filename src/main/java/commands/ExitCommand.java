package commands;

import exceptions.DukeException;

import oop.Storage;
import oop.Ui;

import tasks.TaskList;

/**
 * The ExitCommand class takes care of closing the application
 * when the exit keyword "bye" is input by the user.
 */
public class ExitCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new ExitCommand object,
     isExit is set to true by default.
     */
    public ExitCommand() {
        super();
        isExit = true;
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     * user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     *      an exception occurring in the running of the application.
     */
    public String execute(TaskList task, Ui ui, Storage storage) {
        storage.writeToFile(task);
        return ui.showExitMessage();
    }
}

