package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

/**
 * Parent class for all Commands.
 * Implements the logic that is fundamental to all Commands.
 */
public class Command {
    /**
     * Will save the Tasks in the TaskList via Storage's provided methods.
     * This method is useful as it can be called by child classes should they
     * require saving functionality.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @return String representing Duke's response
     * @throws DukeException re-thrown from underlying method calls.
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        return "Duke has heard you!";
    }
}
